package com.example.anilkothari.weatherapp;

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

    public final static   String BASE_URL = "https://api.darksky.net/forecast/";
    public final static   String SECRET_KEY = "5f3d5ce8510f1352a4ad62da79bdd2e5";
    public final static   String className = NetworkUtils.class.getSimpleName();


    public static String getWeatherUrl(double Latitude, double Longitude){
        String urlString = BASE_URL + SECRET_KEY + "/" + Double.toString(Latitude) +"," + Double.toString(Longitude) ;
        Uri uri = Uri.parse(urlString);

        return uri.toString();
    }




    public static String fetchWeatherDetails(URL url) throws IOException{
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
