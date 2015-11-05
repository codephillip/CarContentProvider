package com.codephillip.intmain.carcontentprovider;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int LOADER_ID = 1;

        getLoaderManager().initLoader(LOADER_ID, null, this);

        int n;
        for (n = 0; n < 4; n++){
            ContentValues contentValues = new ContentValues();
            contentValues.put(CarContract.BigCar.MODEL, "E2"+String.valueOf(n));
            contentValues.put(CarContract.BigCar.SPEED, "79kmpr"+String.valueOf(n));
            contentValues.put(CarContract.BigCar.WEIGHT, "450kg"+String.valueOf(n));
            Uri uri= getContentResolver().insert(CarContract.BigCar.CONTENT_URI, contentValues);
            Log.d("URI_INSERT###", uri.toString());
        }

        int[] views = new int[]{
                R.id.model, R.id.speed,
                R.id.weight
        };

        String[] columns = new String[]{
                CarContract.BigCar.MODEL,  CarContract.BigCar.SPEED,
                CarContract.BigCar.WEIGHT
        };

        adapter = new SimpleCursorAdapter(this, R.layout.row_details, null, columns, views, 0);

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        getContentResolver().delete(CarContract.BigCar.CONTENT_URI, null, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete) {
//            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            long rowsDeleted = getContentResolver().delete(CarContract.BigCar.CONTENT_URI, null, null);
            Log.d("CONTENT_DELETE: ", String.valueOf(rowsDeleted));

            return true;
        }
        else if (id == R.id.insert){
            int k;
            for (k = 0; k < 8; k++){
                ContentValues contentValues = new ContentValues();
                contentValues.put(CarContract.BigCar.MODEL, "E2"+String.valueOf(k));
                contentValues.put(CarContract.BigCar.SPEED, "79kmpr"+String.valueOf(k));
                contentValues.put(CarContract.BigCar.WEIGHT, "450kg"+String.valueOf(k));
                Uri uri= getContentResolver().insert(CarContract.BigCar.CONTENT_URI, contentValues);
                Log.d("URI_INSERT###", uri.toString());
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, CarContract.BigCar.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
