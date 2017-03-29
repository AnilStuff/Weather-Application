package com.example.anilkothari.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener,LoaderManager.LoaderCallbacks<String> {
    public String  mResponseJSON;
    //public TextView mResponseTextView;
    public int LOADER_ID = 99;
    public String SEARCH_QUERY_URL_STRING = "WEATHER_APP_SEARCH_QUERY" ;
    private String CACHE_JSON_STRING = "RESPONSE_CACHE" ;


    public RecyclerView mDailyWeatherList;

    private MyAdapter mAdapter;

protected ProgressBar progressBar;

    protected TextView mTemperature;
    protected TextView mHumidity;
    protected TextView mWindSpeed;
    protected ImageView mIcon;



    @Override
    public Loader<String> onCreateLoader(int id,final Bundle args) {
        return new AsyncTaskLoader<String>(this) {
            String responseJson;

            @Override
            protected void onStartLoading() {
                if (args == null) {
                    return;
                }

                if (responseJson == null){
                    forceLoad();
                } else{
                    deliverResult(responseJson);
                }

                super.onStartLoading();
            }

            @Override
            public String loadInBackground() {
                String query = args.getString(SEARCH_QUERY_URL_STRING);
                try{
                    URL url = new URL(query);
                    return NetworkUtils.fetchWeatherDetails(url);
                }catch (Exception e){
                    e.printStackTrace();
                }

                return null;
            }

            public void deliverResult(String weatherResponse){
                responseJson = weatherResponse;
                 super.deliverResult(weatherResponse);
            }


        };
     }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        mResponseJSON = data;
        deliverForParsingJson(mResponseJSON);
    }

    protected   boolean isConnectedToNetwork(){
        ConnectivityManager manager =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info == null){
            return false;
        }
        if(info.getState()== android.net.NetworkInfo.State.CONNECTED){
            return true;
        }else{
            return false;
        }

     }

    protected void setImageOnImageView(ImageView imv, String imageName){
        imageName=imageName.replace("-","");
        imageName = imageName.toLowerCase();

        int resID = getResources().getIdentifier(imageName , "mipmap", getPackageName());
        imv.setImageResource(resID);

    }
    public void createUserInterfaceWithStore(WeatherStore store){

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mDailyWeatherList.setLayoutManager(layoutManager);

        mDailyWeatherList.setHasFixedSize(true);

        mAdapter = new MyAdapter(store);
        mDailyWeatherList.setAdapter(mAdapter);


//Set current weather details value
        mTemperature.setText("Temperature :" + String.valueOf(store.currentWeatherModel.getTemperature())+"F");
        mHumidity.setText("Humidity :" + String.valueOf(store.currentWeatherModel.getHumidity()));
        mWindSpeed.setText("WindSpeed :" + String.valueOf(store.currentWeatherModel.getWindSpeed())+" Miles/h");
        String iconName = (String.valueOf(store.currentWeatherModel.getIcon()));

        setImageOnImageView(mIcon,iconName);


        hideViews(false);

    }

    public void deliverForParsingJson(String s){
        WeatherParser weatherParser = new WeatherParser(s);
        weatherParser.parseCurrentWeatherDetails();

        WeatherStore store = weatherParser.store;

        createUserInterfaceWithStore(store);
    }

    protected void initializeElements(){
        mTemperature = (TextView) findViewById(R.id.weather_temperature);
        mHumidity = (TextView) findViewById(R.id.weather_humidity);
        mWindSpeed = (TextView) findViewById(R.id.weather_wind);
        mIcon = (ImageView) findViewById(R.id.weather_image_icon);
        progressBar = (ProgressBar) findViewById(R.id.progress_view);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings){
            showSettingsScreen();
        }
        return true;
    }


    protected void showSettingsScreen(){
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        MainActivity.this.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mResponseTextView = (TextView) findViewById(R.id.txt_Weather_Response);
        initializeElements();


        mDailyWeatherList = (RecyclerView) findViewById(R.id.recycler_Weather_Daily_list);

        //Check the cache data is present, if yes show it on textView
        if (savedInstanceState != null) {
            String cacheResponse = savedInstanceState.getString(CACHE_JSON_STRING);
        }

        createConnectionRequest();
        getSharedPreferencesValues();

    }

    protected  void  getSharedPreferencesValues(){

        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
        shared.registerOnSharedPreferenceChangeListener(this);
        boolean temperatueValueInF = shared.getBoolean(getString(R.string.temperature_preference),getResources().getBoolean(R.bool.temperature_preference_F));


    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.temperature_preference))) {
            boolean temperatueValueInF = sharedPreferences.getBoolean(getString(R.string.temperature_preference),getResources().getBoolean(R.bool.temperature_preference_F));


        }
    }

    private void hideViews(Boolean ishide){
        if (ishide){
            mTemperature.setVisibility(View.INVISIBLE);
            mHumidity.setVisibility(View.INVISIBLE);
            mWindSpeed.setVisibility(View.INVISIBLE);
            mIcon.setVisibility(View.INVISIBLE);

            progressBar.setVisibility(View.VISIBLE);
        }else{
            mTemperature.setVisibility(View.VISIBLE);
            mHumidity.setVisibility(View.VISIBLE);
            mWindSpeed.setVisibility(View.VISIBLE);
            mIcon.setVisibility(View.VISIBLE);

            progressBar.setVisibility(View.INVISIBLE);
        }
    }



    protected  void initiateLoaderWithBundle(Bundle queryBundle){
        getSupportLoaderManager().initLoader(LOADER_ID,null,this);

        if (getSupportLoaderManager().getLoader(LOADER_ID) == null) {
            hideViews(true);
            getSupportLoaderManager().initLoader(LOADER_ID, queryBundle, this);
        } else {
            hideViews(true);
            getSupportLoaderManager().restartLoader(LOADER_ID, queryBundle, this);
        }
    }
    protected void createConnectionRequest() {
        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_QUERY_URL_STRING,NetworkUtils.getWeatherUrl(28.7741,77.1025));


        if (isConnectedToNetwork()) {
            initiateLoaderWithBundle(queryBundle);
        }else{
            Toast.makeText(getBaseContext(), "Not Connected to Internet. Please retry",Toast.LENGTH_SHORT).show();
            hideViews(true);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mResponseJSON !=  null){
            outState.putString(CACHE_JSON_STRING,mResponseJSON);
        }
    }
}
