package com.codephillip.intmain.carcontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by codephillip on 11/5/15.
 */
public class CarDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bigcar";
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_QUERY_BIG_CAR = "CREATE TABLE " +
            CarContract.BigCar.TABLE_NAME + " ( " + CarContract.BigCar._ID
            + " INTEGER PRIMARY KEY, " + CarContract.BigCar.MODEL + " TINYTEXT, "+
            CarContract.BigCar.WEIGHT + " TINYTEXT, "+
            CarContract.BigCar.SPEED + " TINYTEXT "+ ")";

    public static final String SQL_QUERY_SMALL_CAR = "CREATE TABLE " +
            CarContract.SmallCar.TABLE_NAME + " ( " + CarContract.SmallCar._ID
            + " INTEGER PRIMARY KEY, " + CarContract.SmallCar.MODEL + " TINYTEXT, "+
            CarContract.SmallCar.WEIGHT + " TINYTEXT, "+
            CarContract.SmallCar.SPEED + " TINYTEXT "+ ")";


    public CarDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_QUERY_BIG_CAR);
        sqLiteDatabase.execSQL(SQL_QUERY_SMALL_CAR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
