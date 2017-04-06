package com.weather;

import com.weather.WeatherModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by anilkothari on 26/03/17.
 */

public class WeatherStore {



    WeatherModel currentWeatherModel;

    ArrayList<WeatherModel> dailyWeatherModelList;

    public ArrayList<WeatherModel> getDailyWeatherModelList() {
        return dailyWeatherModelList;
    }


}

