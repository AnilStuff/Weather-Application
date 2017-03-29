package com.example.anilkothari.weatherapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by anilkothari on 25/03/17.
 */

public class WeatherParser {
    public String response;

    private final static String KEY_SUMMARY = "summary";
    private final static String KEY_ICON = "icon";
    private final static String KEY_TEMPERATURE = "temperature";
    private final static String KEY_APPARENT_TEMPERATURE = "apparentTemperature";
    private final static String KEY_WIND_SPEED = "windSpeed";
    private final static String KEY_VISIBILITY = "visibility";
    private final static String KEY_HUMIDITY = "humidity";
    private final static String KEY_TEMPERATURE_MAX = "temperatureMax";
    private final static String KEY_TEMPERATURE_MIN = "temperatureMin";

    private final static String CURRENT_WEATHER = "currently";
    private final static String DAILY_WEATHER = "daily";
    private final static String HOURLY_WEATHER = "hourly";

    WeatherStore store;


    WeatherParser(String response){
        this.response = response;
        store = new WeatherStore();
    }

    public void parseCurrentWeatherDetails(){

        if (response!= null) {
            try {

                JSONObject jsonObj = new JSONObject(response);

                JSONObject currentWeatherJson = jsonObj.getJSONObject(CURRENT_WEATHER);

                 store.currentWeatherModel = getWeatherModelFromJson(currentWeatherJson);

                JSONObject dailyWeatherPredictionJson = jsonObj.getJSONObject(DAILY_WEATHER);

                store.dailyWeatherModelList = getDailyWeatherModelListFromJson(dailyWeatherPredictionJson);


                JSONObject hourlyWeatherPredictionJson = jsonObj.getJSONObject(HOURLY_WEATHER);

                store.hourlyWeatherModelList = getDailyWeatherModelListFromJson(hourlyWeatherPredictionJson);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    ArrayList<WeatherModel> getDailyWeatherModelListFromJson(JSONObject object){
        ArrayList<WeatherModel> weatherList = new ArrayList<WeatherModel>();

        try {
            JSONArray array = object.getJSONArray("data");

            for (int i=0; i<array.length(); i++){
                WeatherModel model = getWeatherModelFromJson(array.getJSONObject(i));
                weatherList.add(model);
            }



        }catch (Exception e){
            e.printStackTrace();
        }
        return  weatherList;


    }

    private double getDoubleValueFromJsonObject(JSONObject json, String key) {
        double value = 0.00;
        if (json.has(key) && !json.isNull(key)) {
            try {
                  value = json.getDouble(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
         }
        return value;
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






    WeatherModel getWeatherModelFromJson (JSONObject object){
        WeatherModel weatherModel = new WeatherModel();
        try {
            weatherModel.setSummary(getStringValueFromJsonObject(object, KEY_SUMMARY));
            weatherModel.setIcon(getStringValueFromJsonObject(object, KEY_ICON));

            weatherModel.setTemperatureMin(getDoubleValueFromJsonObject(object,KEY_TEMPERATURE_MIN));
            weatherModel.setHumidity(getDoubleValueFromJsonObject(object,KEY_HUMIDITY));

            weatherModel.setTemperatureMax(getDoubleValueFromJsonObject(object,KEY_TEMPERATURE_MAX));
            weatherModel.setTemperatureMin(getDoubleValueFromJsonObject(object,KEY_TEMPERATURE_MIN));
            weatherModel.setTemperature(getDoubleValueFromJsonObject(object,KEY_TEMPERATURE));
            weatherModel.setApparentTemp(getDoubleValueFromJsonObject(object,KEY_APPARENT_TEMPERATURE));

            weatherModel.setWindSpeed(getDoubleValueFromJsonObject(object,KEY_WIND_SPEED));
            weatherModel.setVisibility(getDoubleValueFromJsonObject(object,KEY_VISIBILITY));


        }catch (Exception e){
            e.printStackTrace();
        }

        return weatherModel;
    }

}
