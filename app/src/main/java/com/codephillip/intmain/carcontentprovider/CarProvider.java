package com.codephillip.intmain.carcontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
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
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        carDbHelper = new CarDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
