package com.example.android.fitme.domain;

/**
 * Created by vlad on 16.08.2017.
 */

public class MainItem {
    private String title;
    private int thumbnail;

    public MainItem(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
