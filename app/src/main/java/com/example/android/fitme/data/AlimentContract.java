package com.example.android.fitme.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by vlad on 19.08.2017.
 */

public class AlimentContract {


    public static final String AUTHORITY = "com.example.android.fitme";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_ALIMENTS = "aliments";


    public static final class AlimentEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ALIMENTS).build();

        public static final String TABLE_NAME = "aliments";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PROTEINS = "proteins";
        public static final String COLUMN_CARBS = "carbs";
        public static final String COLUMN_FATS = "fats";

    }

}
