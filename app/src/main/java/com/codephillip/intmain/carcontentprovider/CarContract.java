package com.codephillip.intmain.carcontentprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by codephillip on 11/5/15.
 */
public class CarContract {
    public static final String CONTENT_AUTHORITY = "com.codephillip.intmain.carcontentprovider";
    public static final Uri BASE_URI = Uri.parse("content://"+ CONTENT_AUTHORITY);

    public static final String PATH_BIGCAR = "bigcar";
    public static final String PATH_SMALLCAR = "smallcar";

    public static final class BigCar implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_BIGCAR).build();

        //mime types
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BIGCAR;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BIGCAR;

        public static final String TABLE_NAME = "bigcar";
        public static final String SPEED = "speed";
        public static final String MODEL = "model";
        public static final String WEIGHT = "weight";

        public static final Uri buildUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
    }

    public static final class SmallCar implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_SMALLCAR).build();

        //mime types
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SMALLCAR;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SMALLCAR;

        public static final String TABLE_NAME = "smallcar";
        public static final String SPEED = "speed";
        public static final String MODEL = "model";
        public static final String WEIGHT = "weight";

        public static final Uri buildUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
    }
}
