package com.example.android.fitme.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.fitme.R;
import com.example.android.fitme.data.AlimentContract;
import com.example.android.fitme.domain.Aliment;

import java.util.List;

/**
 * Created by vlad on 22.08.2017.
 */

public class AlimentsAdapter extends RecyclerView.Adapter<AlimentsAdapter.AlimentViewHolder> {

    private Cursor mCursor;
    private Context mContext;

    private static final int VIEW_TYPE_EMPTY_LIST_PLACEHOLDER = 0;
    private static final int VIEW_TYPE_OBJECT_VIEW = 1;


    public AlimentsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public AlimentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutInflater = LayoutInflater.from(mContext).inflate(R.layout.aliment_custom_layout, parent, false);
        return new AlimentViewHolder(layoutInflater);

    }

    @Override
    public void onBindViewHolder(AlimentViewHolder holder, int position) {
        int idIndex = mCursor.getColumnIndex(AlimentContract.AlimentEntry._ID);
        int nameIndex = mCursor.getColumnIndex(AlimentContract.AlimentEntry.COLUMN_NAME);
        int proteinIndex = mCursor.getColumnIndex(AlimentContract.AlimentEntry.COLUMN_PROTEINS);
        int carbsIndex = mCursor.getColumnIndex(AlimentContract.AlimentEntry.COLUMN_CARBS);
        int fatsIndex = mCursor.getColumnIndex(AlimentContract.AlimentEntry.COLUMN_FATS);

        mCursor.moveToPosition(position);

        final int id = mCursor.getInt(idIndex);
        String name = mCursor.getString(nameIndex);
        String protein = mCursor.getString(proteinIndex);
        String carbs = mCursor.getString(carbsIndex);
        String fats = mCursor.getString(fatsIndex);

        holder.itemView.setTag(id);
        holder.tvAlimentName.setText(name);
        holder.tvAlimentProtein.setText(protein);
        holder.tvAlimentCarbs.setText(carbs);
        holder.tvAlimentFats.setText(fats);
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    public class AlimentViewHolder extends RecyclerView.ViewHolder {
        TextView tvAlimentName;
        TextView tvAlimentProtein;
        TextView tvAlimentCarbs;
        TextView tvAlimentFats;

        public AlimentViewHolder(View itemView) {
            super(itemView);
            tvAlimentName = (TextView) itemView.findViewById(R.id.tv_aliment_name);
            tvAlimentProtein = (TextView) itemView.findViewById(R.id.tv_aliment_protein);
            tvAlimentCarbs = (TextView) itemView.findViewById(R.id.tv_aliment_carbs);
            tvAlimentFats = (TextView) itemView.findViewById(R.id.tv_aliment_fats);
        }
    }

    /**
     * When data changes and a re-query occurs, this function swaps the old Cursor
     * with a newly updated Cursor (Cursor c) that is passed in.
     */
    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }

}
