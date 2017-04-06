package com.weather;

import android.util.Log;

import com.weather.WeatherStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by anilkothari on 25/03/17.
 * Package com.Weather
 * Project WeatherApp
 */

 class WeatherParser {
    private final static String KEY_ID = "id";
    private final static String KEY_DESCRIPTION = "description";
    private final static String KEY_ICON = "icon";

    private final static String KEY_TEMPERATURE = "temp";
    private final static String KEY_HUMIDITY = "humidity";
    private final static String KEY_TEMPERATURE_MAX = "temp_max";
    private final static String KEY_TEMPERATURE_MIN = "temp_min";
    private final static String KEY_PRESSURE = "pressure";

    private final static String KEY_WIND_SPEED = "speed";
    private final static String KEY_DEGREE = "deg";

    private final static String KEY_WEATHER = "weather";
    private final static String KEY_MAIN = "main";
    private final static String KEY_WIND = "wind";
    private final static String KEY_CITY_NAME = "name";
    private final static String KEY_DATE_TIME = "dt_txt";

    WeatherStore store;


    WeatherParser(){
        //Create only one instance of store
        if (store == null) {
            store = new WeatherStore();
        }
    }

     void parseCurrentWeatherDetails(String response){

        if (response!= null) {
            try {

                JSONObject jsonObj = new JSONObject(response);

                store.currentWeatherModel = getWeatherModelFromJson(jsonObj);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public void parseForecastDetails(String response){

        if (response!= null) {
            try {

                JSONObject jsonObj = new JSONObject(response);

                store.dailyWeatherModelList = getDailyWeatherModelListFromJson(jsonObj);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    ArrayList<WeatherModel> getDailyWeatherModelListFromJson(JSONObject object){
        ArrayList<WeatherModel> weatherList = new ArrayList<WeatherModel>();

        try {
            JSONArray array = object.getJSONArray("list");

            for (int i=0; i<array.length(); i++){
                WeatherModel model = getWeatherModelFromJson(array.getJSONObject(i));
                weatherList.add(model);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  weatherList;


    }



    WeatherModel getWeatherModelFromJson (JSONObject object){
        WeatherModel weatherModel = new WeatherModel();
        try {

            JSONArray array = object.getJSONArray(KEY_WEATHER);
            JSONObject objectWeather = array.getJSONObject(0);

            weatherModel.setIcon(getStringValueFromJsonObject(objectWeather, KEY_ICON));
            weatherModel.setMain(getStringValueFromJsonObject(objectWeather, KEY_MAIN));
            weatherModel.setDescription(getStringValueFromJsonObject(objectWeather, KEY_DESCRIPTION));
            weatherModel.setId(getDoubleValueFromJsonObject(objectWeather,KEY_ID));


            JSONObject mainObject = object.getJSONObject(KEY_MAIN);

            weatherModel.setHumidity(getDoubleValueFromJsonObject(mainObject,KEY_HUMIDITY));
            weatherModel.setTemperatureMax(getDoubleValueFromJsonObject(mainObject,KEY_TEMPERATURE_MAX));
            weatherModel.setTemperatureMin(getDoubleValueFromJsonObject(mainObject,KEY_TEMPERATURE_MIN));
            weatherModel.setTemperature(getDoubleValueFromJsonObject(mainObject,KEY_TEMPERATURE));
            weatherModel.setPressure(getDoubleValueFromJsonObject(mainObject,KEY_PRESSURE));

            JSONObject wind = object.getJSONObject(KEY_WIND);

            weatherModel.setWindSpeed(getDoubleValueFromJsonObject(wind,KEY_WIND_SPEED));
            weatherModel.setPressure(getDoubleValueFromJsonObject(wind,KEY_DEGREE));

            weatherModel.setCityName(getStringValueFromJsonObject(object, KEY_CITY_NAME));
            weatherModel.setDateTime(getStringValueFromJsonObject(object, KEY_DATE_TIME));


        }catch (Exception e){
            e.printStackTrace();
        }

        return weatherModel;
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





}
