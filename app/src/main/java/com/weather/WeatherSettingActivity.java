package com.weather;

import android.content.Context;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class WeatherSettingActivity extends AppCompatActivity{

    private Button mSubmitButton;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    private EditText mCityName;
    private EditText mCountryName;
    private View mProgressView;

    private TextView mError;

    static int fileCounter = 0;
    static int total_files = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_setting);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        mCityName = (EditText) findViewById(R.id.city);
        mCountryName = (EditText) findViewById(R.id.country);
        mProgressView = (View) findViewById(R.id.SEARCH_PROGRESS);
        mSubmitButton = (Button) findViewById(R.id.submit);
        mError = (TextView) findViewById(R.id.error);

 
    }


    private class CityJSONParser extends AsyncTask<String, String, JSONObject> {


        private JSONObject getCityListJSONObject(Context context, int id){
            Resources res = context.getResources();

            InputStream is = res.openRawResource(id);
            Scanner scan = new Scanner(is);

            StringBuilder builder = new StringBuilder();

            while (scan.hasNextLine()){
                builder.append(scan.nextLine());
            }

            JSONObject jObj;
            try {
                jObj = new JSONObject(builder.toString());
                return jObj;

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }



        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);

            if (isTextFieldsEmpty()){
                //Show Error with
                Log.v("Error", "Incomplete form");
                mError.setText(getResources().getString(R.string.error_incomplete_form));

            }
            else{
                String str_country = getCountry();
                String str_city = getCityName();

                String id = getCityIdentifier(result,str_city,str_country);

                if (id.isEmpty()){
                    //city Not found

                    if (fileCounter == total_files){
                        hideLayoutForProgress(false);
                        mError.setText(getResources().getString(R.string.error_pair_not_found));
                        mError.setVisibility(View.VISIBLE);




                    }else{
                        new CityJSONParser().execute();
                    }

                    }else{
                    //save in persistance storage
                    Log.v("id is ",id);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("id", id);
                    editor.commit();



                    mCityName.setText("");
                    mCountryName.setText("");
                    mError.setText(getResources().getString(R.string.move_to_weather_screen));
                    mError.setVisibility(View.VISIBLE);

                    hideLayoutForProgress(false);


                }




            }


        }

        @Override
        protected JSONObject doInBackground(String... params) {
            CityJSONParser jParser = new CityJSONParser();

            // Getting JSON from URL
            JSONObject json = null ;

            switch (fileCounter){
                case 0:
                    json = jParser.getCityListJSONObject(WeatherSettingActivity.this,R.raw.city);
                    break;
                case 1:
                    json = jParser.getCityListJSONObject(WeatherSettingActivity.this,R.raw.city_1);
                    break;
                case 2:
                    json = jParser.getCityListJSONObject(WeatherSettingActivity.this,R.raw.city_2);
                    break;
                case 3:
                    json = jParser.getCityListJSONObject(WeatherSettingActivity.this,R.raw.city_3);
                    break;
                case 4:
                    json = jParser.getCityListJSONObject(WeatherSettingActivity.this,R.raw.city_4);
                    break;
                case 5:
                    json = jParser.getCityListJSONObject(WeatherSettingActivity.this,R.raw.city_5);
                    break;
                case 6:
                    json = jParser.getCityListJSONObject(WeatherSettingActivity.this,R.raw.city_6);
                    break;
                case 7:
                    json = jParser.getCityListJSONObject(WeatherSettingActivity.this,R.raw.city_7);
                    break;
                case 8:
                    json = jParser.getCityListJSONObject(WeatherSettingActivity.this,R.raw.city_8);
                    break;
                case 9:
                    json = jParser.getCityListJSONObject(WeatherSettingActivity.this,R.raw.city_9);
                    break;
                case 10:
                    json = jParser.getCityListJSONObject(WeatherSettingActivity.this,R.raw.city_10);
                    break;
            }
            fileCounter++;

            return json;
        }



    }

    protected String getCityName(){
       return mCityName.getText().toString();
    }

    protected String getCountry(){
        return mCountryName.getText().toString();
    }

    private boolean isTextFieldsEmpty(){
        return (TextUtils.isEmpty(getCityName()) || TextUtils.isEmpty(getCountry()));
    }


    private JSONObject getCityListJSONObject(){
        Resources res = getResources();

        InputStream is = res.openRawResource(R.raw.city);

        Scanner scan = new Scanner(is);

        StringBuilder builder = new StringBuilder();

        while (scan.hasNextLine()){
            builder.append(scan.nextLine());
        }

        JSONObject object;
        try {
            object = new JSONObject(builder.toString());
            return object;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private String getCityIdentifier(JSONObject object , String cityName, String countryName) {
         String CITY_NAME_KEY = "name";
         String COUNTRY_NAME_KEY = "country";
         String IDENTIFIER_KEY = "_id";

        String identifier = "";

        try {
            JSONArray array = object.getJSONArray("List");


            for (int i=0; i<array.length(); i++){
                 JSONObject cityMap = array.getJSONObject(i);

                if (cityName.equalsIgnoreCase(cityMap.getString(CITY_NAME_KEY))
                        && countryName.equalsIgnoreCase(cityMap.getString(COUNTRY_NAME_KEY)) ){
                    return cityMap.getString(IDENTIFIER_KEY);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  identifier;


    }



    private String getStringValueFromJsonObject(JSONObject json, String key) {
        String value = null;
        if (json.has(key) && !json.isNull(key)) {
            try {
                value = json.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public void validate(View view) {
            //Look for city.jsonFile
        mError.setVisibility(View.INVISIBLE);
        fileCounter = 0;
        bringDownKeyboard();
        hideLayoutForProgress(true);

        new CityJSONParser().execute();

    }


    private void hideLayoutForProgress(boolean isHide){
        if (isHide){
            mProgressView.setVisibility(View.VISIBLE);
         }else{
            mProgressView.setVisibility(View.INVISIBLE);
        }

        mSubmitButton.setEnabled(!isHide);
        mCountryName.setEnabled(!isHide);
        mCityName.setEnabled(!isHide);
    }


    private void bringDownKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

