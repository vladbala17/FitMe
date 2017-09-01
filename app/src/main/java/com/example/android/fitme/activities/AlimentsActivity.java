package com.example.android.fitme.activities;

import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.example.android.fitme.AddAlimentDialog;
import com.example.android.fitme.R;
import com.example.android.fitme.adapters.AlimentsAdapter;
import com.example.android.fitme.data.AlimentContract;

public class AlimentsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = AlimentsActivity.class.getSimpleName() + "++++++++";
    private static final int TASK_LOADER_ID = 0;
    public static final String ADD_ALIMENT_DIALOG = "addAlimentDialog";

    private AlimentsAdapter mAlimentsAdapter;
    private RecyclerView mAlimentsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aliments);

        mAlimentsRecyclerView = (RecyclerView) findViewById(R.id.rv_aliments);
        mAlimentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAlimentsAdapter = new AlimentsAdapter(this);
        mAlimentsRecyclerView.setAdapter(mAlimentsAdapter);

        FloatingActionButton fabAddFood = (FloatingActionButton) findViewById(R.id.fab_add_aliments);
        fabAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAlimentDialog addAlimentDialog = AddAlimentDialog.newInstance();
                addAlimentDialog.show(getSupportFragmentManager(), ADD_ALIMENT_DIALOG);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int id = (int) viewHolder.itemView.getTag();

                String stringId = Integer.toString(id);
                Uri uri = AlimentContract.AlimentEntry.CONTENT_URI;
                uri = uri.buildUpon().appendPath(stringId).build();

                getContentResolver().delete(uri, null, null);

                getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, AlimentsActivity.this);

            }
        }).attachToRecyclerView(mAlimentsRecyclerView);
        getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {
            Cursor mTaskData = null;

            @Override
            protected void onStartLoading() {
                if (mTaskData != null) {
                    deliverResult(mTaskData);
                } else {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {
                try {
                    return getContentResolver().query(AlimentContract.AlimentEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            null);

                } catch (Exception e) {
                    Log.e(TAG, "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(Cursor data) {
                mTaskData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAlimentsAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAlimentsAdapter.swapCursor(null);
    }
}
