package com.example.android.fitme.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by vlad on 19.08.2017.
 */

public class AlimentContentProvider extends ContentProvider {

    public static final int ALIMENTS = 100;
    public static final int ALIMENTS_WITH_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private AlimentsDbHelper mAlimentsDbHelper;

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AlimentContract.AUTHORITY, AlimentContract.PATH_ALIMENTS, ALIMENTS);
        uriMatcher.addURI(AlimentContract.AUTHORITY, AlimentContract.PATH_ALIMENTS + "/#", ALIMENTS_WITH_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mAlimentsDbHelper = new AlimentsDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mAlimentsDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);

        Cursor returnedCursor;
        switch (match) {
            case ALIMENTS:
                returnedCursor = db.query(AlimentContract.AlimentEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case ALIMENTS_WITH_ID:
                String id = uri.getPathSegments().get(1);
                String mSelection = "_id=?";
                String[] mSelectionArgs = new String[]{id};

                returnedCursor = db.query(AlimentContract.AlimentEntry.TABLE_NAME,
                        projection,
                        mSelection,
                        mSelectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("This uri is not supported");
        }
        returnedCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnedCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = mAlimentsDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        Uri contentUri;
        switch (match) {
            case ALIMENTS:
                long id = db.insert(AlimentContract.AlimentEntry.TABLE_NAME, null, values);
                if (id > 0) {
                    contentUri = ContentUris.withAppendedId(AlimentContract.AlimentEntry.CONTENT_URI, id);
                } else {
                    throw new SQLException("Failed to insert");
                }
                break;
            default:
                throw new UnsupportedOperationException("This operation is not supported" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return contentUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mAlimentsDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        int deleteResult;
        switch (match) {
            case ALIMENTS_WITH_ID:
                String id = uri.getPathSegments().get(1);
                String mSelection = "_id=?";
                String[] mSelectionArgs = new String[]{id};
                deleteResult = db.delete(AlimentContract.AlimentEntry.TABLE_NAME, mSelection, mSelectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("This operation is not supported");

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return deleteResult;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
