package com.example.android.fitme;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.fitme.adapters.MenuAdapter;
import com.example.android.fitme.data.AlimentContract;
import com.example.android.fitme.domain.MainItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mCardViewRecyclerView;
    private MenuAdapter mMenuAdapter;
    private List<MainItem> mMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        mCardViewRecyclerView = (RecyclerView) findViewById(R.id.rv_card_view);
        mMenuList = new ArrayList<>();
        mMenuAdapter = new MenuAdapter(this, mMenuList);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mCardViewRecyclerView.setLayoutManager(linearLayoutManager);
        mCardViewRecyclerView.setAdapter(mMenuAdapter);
        prepareMenuItems();
        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    private void prepareMenuItems() {
        int[] thumbnails = new int[]{
                R.drawable.food,
                R.drawable.meal,
                R.drawable.progress,
                R.drawable.setting
        };

        MainItem mainItemFood = new MainItem("Aliments", thumbnails[0]);
        MainItem mainItemMeals = new MainItem("Meals", thumbnails[1]);
        MainItem mainItemRaports = new MainItem("Progress", thumbnails[2]);
        MainItem mainItemSettings = new MainItem("Settings", thumbnails[3]);

        mMenuList.add(mainItemFood);
        mMenuList.add(mainItemMeals);
        mMenuList.add(mainItemRaports);
        mMenuList.add(mainItemSettings);

        mMenuAdapter.notifyDataSetChanged();

    }

}