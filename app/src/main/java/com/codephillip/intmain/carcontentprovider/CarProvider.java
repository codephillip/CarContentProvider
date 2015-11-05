package com.codephillip.intmain.carcontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class CarProvider extends ContentProvider {
    CarDbHelper carDbHelper;

    private static final int PATH_BIGCAR = 100;
    private static final int PATH_BIGCAR_WITH_ITEM = 101;
    private static final int PATH_SMALLCAR = 200;
    private static final int PATH_SMALLCAR_WITH_ITEM = 201;

    UriMatcher sUriMatcher = buildUriMatcher();

    private UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CarContract.CONTENT_AUTHORITY;
        uriMatcher.addURI(authority, CarContract.PATH_BIGCAR, PATH_BIGCAR);
        uriMatcher.addURI(authority, CarContract.PATH_BIGCAR+"/*", PATH_BIGCAR_WITH_ITEM);
        uriMatcher.addURI(authority, CarContract.PATH_SMALLCAR, PATH_SMALLCAR);
        uriMatcher.addURI(authority, CarContract.PATH_SMALLCAR+"/*", PATH_SMALLCAR_WITH_ITEM);
        return uriMatcher;
    }

    public CarProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)){
            case PATH_BIGCAR:
                return CarContract.BigCar.CONTENT_TYPE;
            case PATH_BIGCAR_WITH_ITEM:
                return CarContract.BigCar.CONTENT_ITEM_TYPE;
            case PATH_SMALLCAR:
                return CarContract.SmallCar.CONTENT_TYPE;
            case PATH_SMALLCAR_WITH_ITEM:
                return CarContract.SmallCar.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown Uri: "+uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri retUri;
        SQLiteDatabase db = carDbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)){
            case PATH_BIGCAR:{
                long id = db.insert(CarContract.BigCar.TABLE_NAME, null, values);
                if (id > 0){
                    retUri = CarContract.BigCar.buildUri(id);
                }
                else {
                    throw new android.database.SQLException("Failed to insert into table: "+uri);
                }
                break;
            }
            case PATH_SMALLCAR:{
                long id = db.insert(CarContract.SmallCar.TABLE_NAME, null, values);
                if (id > 0){
                    retUri = CarContract.SmallCar.buildUri(id);
                }
                else {
                    throw new android.database.SQLException("Failed to insert into table: "+uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown Uri "+uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return retUri;
    }

    @Override
    public boolean onCreate() {
        carDbHelper = new CarDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        SQLiteDatabase db = carDbHelper.getReadableDatabase();
        switch (sUriMatcher.match(uri)){
            case PATH_BIGCAR:
                cursor = db.query(
                        CarContract.BigCar.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                        );
                break;
            case PATH_BIGCAR_WITH_ITEM:
                cursor = db.query(
                        CarContract.BigCar.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                        );
                break;
            case PATH_SMALLCAR:
                cursor = db.query(
                        CarContract.SmallCar.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case PATH_SMALLCAR_WITH_ITEM:
                cursor = db.query(
                        CarContract.SmallCar.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
