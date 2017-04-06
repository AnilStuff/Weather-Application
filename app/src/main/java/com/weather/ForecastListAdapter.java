package com.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by anilkothari on 04/04/17.
 * Project WeatherApp
 */
/*

* @startuml

* car --|> wheel

* @enduml
*/
public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.Viewholder>{

    private WeatherStore weatherStore;
    private Context context;

     static final String  ICON_URL_IDENTIFIER =  "http://openweathermap.org/img/w/";
     static final String  ICON_EXTENSION = ".png";

    /**
     * @param store : Pass the weather store singleton object containing data of forecast
     * @param context : context to show the image icon
     */
    ForecastListAdapter(WeatherStore store, Context context){
        this.weatherStore = store;
        this.context = context;
    }

    static class Viewholder extends RecyclerView.ViewHolder{

        private TextView windspeed;
        private TextView temperatureMin;
        private ImageView icon;
        private TextView summary;
        TextView temperatureMax;
        private TextView temperature;
        private TextView dateTime;



         Viewholder(View itemView) {
            super(itemView);
               windspeed = (TextView) itemView.findViewById(R.id.txt_weather_windspeed);
               temperatureMin= (TextView) itemView.findViewById(R.id.txt_weather_min_temperature);
               icon= (ImageView) itemView.findViewById(R.id.weather_image_icon);
               summary= (TextView) itemView.findViewById(R.id.txt_summary);
               temperatureMax= (TextView) itemView.findViewById(R.id.txt_weather_max_temperature);
               dateTime = (TextView) itemView.findViewById(R.id.txt_date_time);
               temperature= (TextView) itemView.findViewById(R.id.txt_weather_temperature);

          }

        private String getURlFromIdentifier(String identifier){
            return  ICON_URL_IDENTIFIER + identifier + ICON_EXTENSION;
        }


        void bind(WeatherModel model, Context context) {

            String windspeedValue;
            windspeedValue = context.getString(R.string.WindTextView)
                            + String.valueOf(model.getWindSpeed())
                            + context.getString(R.string.MeterPerSecond);
            windspeed.setText(windspeedValue);

            String temperatureMinValue;
            temperatureMinValue = context.getString(R.string.TemperatureMinTextView)
                    + String.valueOf(model.getTemperatureMin())
                    + context.getString(R.string.DegreeCelcius);
            temperatureMin.setText(temperatureMinValue);

            String temperatureValue;
            temperatureValue = context.getString(R.string.TemperatureTextView)
                    + String.valueOf(model.getTemperature())
                    + context.getString(R.string.DegreeCelcius);
            temperature.setText(temperatureValue);

            String temperatureMaxValue;
            temperatureMaxValue = context.getString(R.string.TemperatureMaxTextView)
                    + String.valueOf(model.getTemperatureMax())
                    + context.getString(R.string.DegreeCelcius);
            temperatureMax.setText(temperatureMaxValue);

            String dateTimeValue;
            dateTimeValue = context.getString(R.string.DateTextView) +   String.valueOf(model.getDateTime());
            dateTime.setText(dateTimeValue);

            String summaryValue;
            summaryValue = context.getString(R.string.SummaryTextView) +   String.valueOf(model.getDescription());
            summary.setText(summaryValue);

            Picasso.with(context)
                    .load(getURlFromIdentifier(String.valueOf(model.getIcon())))
                    .resize(144,144)
                    .into(icon);


        }
    }



    @Override
    public ForecastListAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder_view,parent,false);
        return new Viewholder(view);
    }

    @Override
    public int getItemCount() {
        return weatherStore.dailyWeatherModelList.size();
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        ArrayList<WeatherModel> weatherModelList = weatherStore.getDailyWeatherModelList();
        WeatherModel model = weatherModelList.get(position);
        holder.bind(model,context);

    }
}

