package com.weather;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URL;



public class WeatherActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    public String  mResponseWeatherJSON;
    public String  mResponseForecastJSON;

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;

    public int WEATHER_LOADER_ID = 199;
    public int FORECAST_LOADER_ID = 299;

    public String QUERY_URL_WEATHER = "WEATHER_APP_SEARCH_QUERY" ;
    public String QUERY_URL_FORECAST = "FORECAST_APP_SEARCH_QUERY" ;

    private String WEATHER_CACHE_JSON_STRING = "WEATHER_RESPONSE_CACHE" ;
    private String FORECAST_CACHE_JSON_STRING = "FORECAST_RESPONSE_CACHE" ;

    private String mId;
    public static final String  ICON_URL_IDENTIFIER =  "http://openweathermap.org/img/w/";
    public static final String  ICON_EXTENSION = ".png";

    public RecyclerView mDailyWeatherList;
    private ForecastListAdapter mAdapter;
    protected ProgressBar progressBar;

    protected TextView mTemperature;
    protected TextView mHumidity;
    protected TextView mWindSpeed;
    protected ImageView mIcon;
    protected TextView mCityName;
    protected TextView mError;


    WeatherParser weatherParser;


    @Override
    public Loader<String> onCreateLoader(final int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {
            String weatherResponse;
            String forecastResponse;

            @Override
            protected void onStartLoading() {
                if (args == null) {
                    return;
                }

                if (id == WEATHER_LOADER_ID) {
                    if (weatherResponse == null) {
                        forceLoad();
                    } else {
                        deliverResult(weatherResponse);
                    }
                }else{
                    if (forecastResponse == null) {
                        forceLoad();
                    } else {
                        deliverResult(forecastResponse);
                    }
                }

                super.onStartLoading();
            }

            @Override
            public String loadInBackground() {
                if (id == WEATHER_LOADER_ID) {
                    try {
                        URL url = new URL(args.getString(QUERY_URL_WEATHER));
                        return NetworkUtils.fetchDetails(url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        URL url = new URL(args.getString(QUERY_URL_FORECAST));
                        return NetworkUtils.fetchDetails(url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }

            public void deliverResult(String response){
                if (id == WEATHER_LOADER_ID) {
                    weatherResponse = response;
                    super.deliverResult(weatherResponse);
                }else{
                    forecastResponse = response;
                    super.deliverResult(forecastResponse);
                }
            }


        };
     }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (loader.getId() == WEATHER_LOADER_ID){
            mResponseWeatherJSON = data;
            parseWeatherData(mResponseWeatherJSON);
        }else{
            mResponseForecastJSON = data;
            parseForecastData(mResponseForecastJSON);
        }
    }

    protected void setImageOnImageView(ImageView imv, String identifier){
        String url = ICON_URL_IDENTIFIER + identifier + ICON_EXTENSION;
        Picasso.with(WeatherActivity.this)
                .load(url)
                .resize(144,144)
                .into(imv);
    }

    public void createCurrentWeatherUIWithStore(WeatherStore store) {
        //Set current weather details value
        mTemperature.setText("Temperature :" + String.valueOf(store.currentWeatherModel.getTemperature())+"°C");
        mHumidity.setText("Humidity :" + String.valueOf(store.currentWeatherModel.getHumidity()));
        mWindSpeed.setText("WindSpeed :" + String.valueOf(store.currentWeatherModel.getWindSpeed())+" m/s");
        String iconName = (String.valueOf(store.currentWeatherModel.getIcon()));
        setImageOnImageView(mIcon,iconName);
        mCityName.setText("Weather in " + String.valueOf(store.currentWeatherModel.getCityName()));



        hideViews(false);
    }
    public void createForecastWeatherUIWithStore(WeatherStore store){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mDailyWeatherList.setLayoutManager(layoutManager);

        mDailyWeatherList.setHasFixedSize(true);

        mAdapter = new ForecastListAdapter(store, WeatherActivity.this);
        mDailyWeatherList.setAdapter(mAdapter);
    }

    public void parseWeatherData(String s){
        weatherParser.parseCurrentWeatherDetails(s);
        createCurrentWeatherUIWithStore(weatherParser.store);
    }

    public void parseForecastData(String s){
        weatherParser.parseForecastDetails(s);

        //Check here to show alert

        WeatherAlert weatherAlert = new WeatherAlert();

        // No data is present so no need to check alert behaviour
        if (weatherParser.store.getDailyWeatherModelList().size() > 0){
            if (weatherAlert.isAlertNeedToBeShown(weatherParser.store)) {
                showNotifications(weatherAlert);
            }
        }

        createForecastWeatherUIWithStore(weatherParser.store);
    }


    protected void initializeElements(){
        mTemperature = (TextView) findViewById(R.id.weather_temperature);
        mHumidity = (TextView) findViewById(R.id.weather_humidity);
        mWindSpeed = (TextView) findViewById(R.id.weather_wind);
        mIcon = (ImageView) findViewById(R.id.weather_image_icon);
        progressBar = (ProgressBar) findViewById(R.id.progress_view);
        mCityName = (TextView) findViewById(R.id.city_name);
        mError =  (TextView) findViewById(R.id.error);
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
        Intent intent = new Intent(WeatherActivity.this, WeatherSettingActivity.class);
        WeatherActivity.this.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherParser = new WeatherParser();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        setContentView(R.layout.activity_main);
        //mResponseTextView = (TextView) findViewById(R.id.txt_Weather_Response);
        initializeElements();


        mDailyWeatherList = (RecyclerView) findViewById(R.id.recycler_Weather_Daily_list);

        //Check the cache data is present, if yes show it on textView
        if (savedInstanceState != null) {
            String cacheWeather = savedInstanceState.getString(WEATHER_CACHE_JSON_STRING);
            String cacheForecast = savedInstanceState.getString(FORECAST_CACHE_JSON_STRING);

            if (cacheWeather != null && cacheForecast !=null) {
                parseWeatherData(cacheWeather);
                parseForecastData(cacheForecast);

            }

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        String id = sharedpreferences.getString("id","");
        mError.setVisibility(View.INVISIBLE);

        if (!id.isEmpty() && mId == null){
            mId = id;
            createConnectionRequest();
            return;
        }

        if (id.isEmpty()){
            mError.setVisibility(View.VISIBLE);
            mError.setText(getResources().getString(R.string.error_go_to_setting));


        }else{

            if (mId == null){
                mId = id;
                createConnectionRequest();
            }
            else {

                if (mId.equalsIgnoreCase(id)) {

                } else {
                    createConnectionRequest();
                }

            }
        }
    }



    private void hideViews(Boolean ishide){
        if (ishide){
            mTemperature.setVisibility(View.INVISIBLE);
            mHumidity.setVisibility(View.INVISIBLE);
            mWindSpeed.setVisibility(View.INVISIBLE);
            mIcon.setVisibility(View.INVISIBLE);
            mCityName.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }else{
            mTemperature.setVisibility(View.VISIBLE);
            mHumidity.setVisibility(View.VISIBLE);
            mWindSpeed.setVisibility(View.VISIBLE);
            mIcon.setVisibility(View.VISIBLE);
            mCityName.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }



    protected  void initiateLoaderWithBundle(Bundle queryBundle){
        getSupportLoaderManager().initLoader(WEATHER_LOADER_ID,null,this);
        getSupportLoaderManager().initLoader(FORECAST_LOADER_ID,null,this);

        if (getSupportLoaderManager().getLoader(WEATHER_LOADER_ID) == null) {
            hideViews(true);
            getSupportLoaderManager().initLoader(WEATHER_LOADER_ID, queryBundle, this);
        } else {
            hideViews(true);
            getSupportLoaderManager().restartLoader(WEATHER_LOADER_ID, queryBundle, this);
        }

        if (getSupportLoaderManager().getLoader(FORECAST_LOADER_ID) == null) {
            hideViews(true);
            getSupportLoaderManager().initLoader(FORECAST_LOADER_ID, queryBundle, this);
        } else {
            hideViews(true);
            getSupportLoaderManager().restartLoader(FORECAST_LOADER_ID, queryBundle, this);
        }

    }
    protected void createConnectionRequest() {
        Bundle queryBundle = new Bundle();
        String id = sharedpreferences.getString("id","");

        queryBundle.putString(QUERY_URL_WEATHER,NetworkUtils.getWeatherUrl(id));
        queryBundle.putString(QUERY_URL_FORECAST,NetworkUtils.getForecastUrl(id));

        ConnectivityManager manager =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (NetworkUtils.isConnectedToNetwork(manager)) {
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

        if (mResponseWeatherJSON !=  null){
            outState.putString(WEATHER_CACHE_JSON_STRING,mResponseWeatherJSON);
        }

        if (mResponseForecastJSON != null){
            outState.putString(FORECAST_CACHE_JSON_STRING,mResponseForecastJSON);
        }
        
        
    }


    protected void showNotifications(WeatherAlert weatherAlert){
        Intent intent = new Intent(getBaseContext(), WeatherActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(getBaseContext());

        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(weatherAlert.getTitle())
                .setContentText(weatherAlert.getMessage())
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo("");


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b.build());
    }
}
