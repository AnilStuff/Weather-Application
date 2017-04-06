package com.weather;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by anilkothari on 24/03/17.
 */


public class NetworkUtils  {

    public final static   String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public final static   String WEATHER = "weather";
    public final static   String FORECAST = "forecast";

    public final static   String SECRET_KEY = "7058048a96160a703a9999c966738388";
    public final static   String className = NetworkUtils.class.getSimpleName();


    public static boolean isConnectedToNetwork(ConnectivityManager manager){
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

    public static String getWeatherUrl(String identifier){

        String urlString = BASE_URL + WEATHER  + "?id="  + identifier + "&units=metric&appid=" + SECRET_KEY;
        Uri uri = Uri.parse(urlString);
        return uri.toString();
    }

    public static String getForecastUrl(String identifier){
        String urlString = BASE_URL + FORECAST  + "?id="  + identifier + "&units=metric&appid=" + SECRET_KEY;
        Uri uri = Uri.parse(urlString);
        return uri.toString();
    }


    public static String fetchDetails(URL url) throws IOException{
         HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {

            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasNext = scanner.hasNext();
            if (hasNext){
                return scanner.next();
            }else{
                return null;
            }

        }finally {
            urlConnection.disconnect();
        }


    }



}
