package com.example.anilkothari.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anilkothari on 26/03/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Viewholder>{

    public  WeatherStore weatherStore;

    MyAdapter(WeatherStore store){
        this.weatherStore = store;
    }

    public static class Viewholder extends RecyclerView.ViewHolder{

        private TextView windspeed;
        private TextView humidity;
        private TextView icon;

        private TextView summary;
        private TextView temperatureMax;


        public Viewholder(View itemView) {
            super(itemView);
               windspeed = (TextView) itemView.findViewById(R.id.txt_weather_windspeed);
               humidity= (TextView) itemView.findViewById(R.id.txt_weather_humidity);
               icon= (TextView) itemView.findViewById(R.id.txt_weather_icon);

               summary= (TextView) itemView.findViewById(R.id.txt_weather_summary);
               temperatureMax= (TextView) itemView.findViewById(R.id.txt_weather_max_temperature);


        }


        public void bind(WeatherModel model) {

            windspeed.setText("Wind :"+String.valueOf(model.getWindSpeed())+" Miles/h") ;
            humidity.setText("Humidity :"+String.valueOf(model.getHumidity()));
            icon.setText("Icon :"+String.valueOf(model.getIcon()));
            summary.setText("Summary :"+String.valueOf(model.getSummary()));
            String s = "Temperature :"+String.valueOf(model.getTemperatureMax())+ "F";
            temperatureMax.setText(s);

        }
    }



    @Override
    public MyAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder_view,parent,false);
         Viewholder vw = new Viewholder(view);
        return vw;
    }

    @Override
    public int getItemCount() {
        return weatherStore.dailyWeatherModelList.size();
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        ArrayList<WeatherModel> weatherModelList = weatherStore.getDailyWeatherModelList();
        WeatherModel model = weatherModelList.get(position);
        holder.bind(model);

    }
}
