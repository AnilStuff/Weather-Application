package com.example.anilkothari.weatherapp;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by anilkothari on 26/03/17.
 */

public class WeatherStore {

    WeatherModel currentWeatherModel;

    ArrayList<WeatherModel> dailyWeatherModelList;

    ArrayList<WeatherModel> hourlyWeatherModelList;

    public ArrayList<WeatherModel> getDailyWeatherModelList() {
        return dailyWeatherModelList;
    }
}

