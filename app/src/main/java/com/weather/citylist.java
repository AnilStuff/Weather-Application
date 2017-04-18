package com.weather;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.weather.com.city.CityListAdapter;

public class citylist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);

     }

    @Override
    protected void onStart() {
        super.onStart();
        showCountryList();
    }

    private void showCountryList() {
        CityListAdapter mDbHelper = new CityListAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor countryListCursor = mDbHelper.getCountryList();

        try {
            while (countryListCursor.moveToNext()) {
                String country = countryListCursor.getString(countryListCursor.getColumnIndex("country"));

                Log.v("CityList", country);
            }
        } finally {
            countryListCursor.close();
        }


        mDbHelper.close();
    }
}
