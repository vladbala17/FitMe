package com.example.android.fitme.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.fitme.R;
import com.example.android.fitme.domain.MainItem;

import java.util.List;

/**
 * Created by vlad on 16.08.2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private Context mContext;
    private List<MainItem> mMenuList;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflateView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_menu_layout, parent, false);
        return new MyViewHolder(inflateView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MainItem mainItem = mMenuList.get(position);
        holder.title.setText(mainItem.getTitle());
        Glide.with(mContext).load(mainItem.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return mMenuList.size();
    }

    public MenuAdapter(Context mContext, List<MainItem> mMenuList) {
        this.mContext = mContext;
        this.mMenuList = mMenuList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_menu_title);
            thumbnail = (ImageView) view.findViewById(R.id.iv_menu_image);
        }
    }
}
