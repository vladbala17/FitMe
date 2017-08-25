package com.example.android.fitme.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vlad on 19.08.2017.
 */

public class AlimentsDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "aliments.db";
    private static final int DATABASE_VERSION = 1;

    public AlimentsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE " + AlimentContract.AlimentEntry.TABLE_NAME + " (" +
                AlimentContract.AlimentEntry._ID + " INTEGER PRIMARY KEY, " +
                AlimentContract.AlimentEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                AlimentContract.AlimentEntry.COLUMN_PROTEINS + " TEXT NOT NULL, " +
                AlimentContract.AlimentEntry.COLUMN_CARBS + " TEXT NOT NULL, " +
                AlimentContract.AlimentEntry.COLUMN_FATS + " TEXT NOT NULL);";


        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AlimentContract.AlimentEntry.TABLE_NAME);
        onCreate(db);
    }
}
