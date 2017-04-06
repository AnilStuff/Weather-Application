package com.weather;

import java.util.ArrayList;

/**
 * Created by anilkothari on 04/04/17.
 */

enum ALERT_TYPE{
    HIGH_TEMP,
    LOW_TEMP,
    RAIN,
    VOILENT_STORM,
    STORM,
    SEVERE_GALE,
    GALE,
    HIGH_WIND,
    TORNADO,
    TROPICAL_STORM,
    HURRICANE,
    WINDY,
    HAIL,

    OVERCAST_CLOUDY,
    MIST,SMOKE,HAZE,DUST,FOG,SAND,SAND_DUST,VOLCANIC_ASH,SQUALLS,


    HEAVY_SNOW, HEAVY_SHOWER_SNOW,RAIN_AND_SNOW,
    HEAVY_INTENSITY_RAIN, VERY_HEAVY_RAIN, EXTREME_RAIN,
    FREEZING_RAIN,LIGHT_INTENSITY_SHOWER_RAIN,SHOWER_RAIN,HEAVY_INTENSITY_SHOWER_RAIN,

    LIGHT_INTENSITY_DRIZZLE, DRIZZLE, LIGHT_INTENSITY_DRIZZLE_RAIN, HEAVY_INTENSITY_DRIZZLE_RAIN,
    HEAVY_SHOWER_RAIN_AND_DRIZZLE, SHOWER_DRIZZLE,SHOWER_RAIN_AND_DRIZZLE,

    THUNDERSTORM_LIGHT_RAIN,
    THUNDERSTORM_WITH_RAIN,
    THUNDERSTORM_HEAVY_RAIN,
    THUNDERSTORM,
    LIGHT_THUNDER_STORM,
    HEAVY_THUNDER_STORM,
    RAGGED_THUNDER_STORM,
    THUNDERSTROM_LIGHT_DRIZZLE,
    THUNDERSTROM_DRIZZLE,
    THUNDERSTROM_HEAVY_DRIZZLE,


}

class WeatherAlert {
    /* public ALERT_TYPE alert; */


    private void setWeatherAlertMessage(ALERT_TYPE alert_type){

         String  HIGH_TEMP_TITLE = "High Temperature";
         String HIGH_TEMP_MESSAGE = "Temperature is too high. Please carry an umbrella and a bottle of water when going outside";

         String LOW_TEMP_TITLE = "Low Temperature";
         String LOW_TEMP_MESSAGE = "Temperature is too low. Please wear winter clothes when going outside";

         String FOG_TITLE = "Foggy Weather";
         String FOG_MESSAGE = "Please check your vehicle head lights before going out";

         String RAIN_TITLE = "Rainy";
         String RAIN_MESSAGE = "Please carry an umbrella when going outside";

         String WIND_TITLE = "Windy";
         String WIND_MESSAGE = "The weather outside is too much windy. Please try to avoid going outside";


         String TORNADO_TITLE = "Prediction: Tornado";
         String TORNADO_MESSAGE = "Please go to a pre-designated area such as a safe room, basement, storm cellar, or the lowest building level.";

         String TROPICAL_STORM_TITLE = "Prediction: Tropical storm coming";
         String TROPICAL_STORM_MESSAGE = "Please stock up necessary items, if you are willing to stay";

         String HURRICANE_TITLE = "Prediction: Hurricane";
         String HURRICANE_MESSAGE = "Secure your home, close storm shutters, and secure outdoor objects or bring them indoors.";
//
//         String WINDY_TITLE = "Prediction: Windy";
//         String WINDY_MESSAGE = "Severe winds outside, Please avoid going out, if not necessary";

         String HAIL_TITLE = "Prediction: Hail";
         String HAIL_MESSAGE = "Stay inside in your ";

         String HIGH_WIND_TITLE = "Prediction: High Windy";
         String HIGH_WIND_MESSAGE = "High Winds outside, Please avoid going out, if not necessary";

         String GALE_TITLE = "Prediction: STRONG WINDS";
         String GALE_MESSAGE = "High Winds outside, Please avoid going out, if not extremely urgent";

         String SEVERE_GALE_TITLE = "Prediction: VERY STRONG WINDS";
         String SEVERE_GALE_MESSAGE = "High Winds outside, Please avoid going out, if not extremely urgent";

         String STORM_TITLE = "Prediction: STORM";
         String STORM_MESSAGE = "High Winds outside, Please avoid going out, if not extremely urgent";

         String VOILENT_STORM_TITLE = "Prediction: VOILENT STORM";
         String VOILENT_STORM_MESSAGE = "Voilent strom outside, Please avoid going out, if not extremely urgent";


         String OVERCAST_CLOUDY_TITLE = "Prediction: Overcast cloudy";
         String OVERCAST_CLOUDY_MESSAGE = "Overcast cloudy weather, Rain is predicted in near time";


         String MIST_TITLE = "Prediction: Misty";
         String MIST_MESSAGE = "Misty weather is coming";

         String SMOKE_TITLE = "Prediction: Smoke";
         String SMOKE_MESSAGE = "Smoky weather is coming";

         String HAZE_TITLE = "Prediction : HAZE";
         String HAZE_MESSAGE = "Hazy weather is coming";

         String SAND_DUST_TITLE = "Prediction : HAZE";
         String SAND_DUST_MESSAGE = "";


         String SAND_TITLE = "Prediction : Sand";
         String SAND_MESSAGE = "Sandy weather is coming";

         String DUST_TITLE ="Prediction : Dust";
         String DUST_MESSAGE = "Allergic to dust, please avoid going outside";

         String VOLCANIC_ASH_TITLE ="Prediction : Volcanic ash";
         String VOLCANIC_ASH_MESSAGE = "Volcanic ash is coming";

        String SQUALLS_TITLE ="Prediction : Squalls";
         String SQUALLS_MESSAGE = "A sudden violent gust of wind or localized storm, especially one bringing rain, snow, or sleet.";


        switch (alert_type){
            case HIGH_TEMP:
                setTitle(HIGH_TEMP_TITLE);
                setMessage(HIGH_TEMP_MESSAGE);
                break;
            case RAIN: setTitle(RAIN_TITLE); setMessage(RAIN_MESSAGE);break;
            case FOG: setTitle(FOG_TITLE); setMessage(FOG_MESSAGE);break;
            case WINDY: setTitle(WIND_TITLE); setMessage(WIND_MESSAGE);break;
            case LOW_TEMP: setTitle(LOW_TEMP_TITLE); setMessage(LOW_TEMP_MESSAGE);break;
            case VOILENT_STORM: setTitle(VOILENT_STORM_TITLE); setMessage(VOILENT_STORM_MESSAGE);break;
            case STORM: setTitle(STORM_TITLE); setMessage(STORM_MESSAGE);break;
            case SEVERE_GALE: setTitle(SEVERE_GALE_TITLE); setMessage(SEVERE_GALE_MESSAGE);break;
            case HIGH_WIND: setTitle(HIGH_WIND_TITLE); setMessage(HIGH_WIND_MESSAGE);break;
            case TORNADO: setTitle(TORNADO_TITLE); setMessage(TORNADO_MESSAGE);break;
            case TROPICAL_STORM: setTitle(TROPICAL_STORM_TITLE); setMessage(TROPICAL_STORM_MESSAGE);break;
            case HURRICANE: setTitle(HURRICANE_TITLE); setMessage(HURRICANE_MESSAGE);break;
            case HAIL: setTitle(HAIL_TITLE); setMessage(HAIL_MESSAGE);break;
            case OVERCAST_CLOUDY: setTitle(OVERCAST_CLOUDY_TITLE); setMessage(OVERCAST_CLOUDY_MESSAGE);break;
            case MIST: setTitle(MIST_TITLE); setMessage(MIST_MESSAGE);break;
            case SMOKE: setTitle(SMOKE_TITLE); setMessage(SMOKE_MESSAGE);break;
            case HAZE: setTitle(HAZE_TITLE); setMessage(HAZE_MESSAGE);break;
            case DUST: setTitle(DUST_TITLE); setMessage(DUST_MESSAGE);break;
            case SAND: setTitle(SAND_TITLE); setMessage(SAND_MESSAGE);break;
            case SAND_DUST: setTitle(SAND_DUST_TITLE); setMessage(SAND_DUST_MESSAGE);break;
            case VOLCANIC_ASH: setTitle(VOLCANIC_ASH_TITLE); setMessage(VOLCANIC_ASH_MESSAGE);break;
            case SQUALLS: setTitle(SQUALLS_TITLE); setMessage(SQUALLS_MESSAGE);break;
            case GALE: setTitle(GALE_TITLE); setMessage(GALE_MESSAGE);break;

            case HEAVY_SNOW:
                break;
            case HEAVY_SHOWER_SNOW:
                break;
            case RAIN_AND_SNOW:
                break;
            case HEAVY_INTENSITY_RAIN:
                break;
            case VERY_HEAVY_RAIN:
                break;
            case EXTREME_RAIN:
                break;
            case FREEZING_RAIN:
                break;
            case LIGHT_INTENSITY_SHOWER_RAIN:
                break;
            case SHOWER_RAIN:
                break;
            case HEAVY_INTENSITY_SHOWER_RAIN:
                break;
            case LIGHT_INTENSITY_DRIZZLE:
                break;
            case DRIZZLE:
                break;
            case LIGHT_INTENSITY_DRIZZLE_RAIN:
                break;
            case HEAVY_INTENSITY_DRIZZLE_RAIN:
                break;
            case HEAVY_SHOWER_RAIN_AND_DRIZZLE:
                break;
            case SHOWER_DRIZZLE:
                break;
            case SHOWER_RAIN_AND_DRIZZLE:
                break;
            case THUNDERSTORM_LIGHT_RAIN:
                break;
            case THUNDERSTORM_WITH_RAIN:
                break;
            case THUNDERSTORM_HEAVY_RAIN:
                break;
            case THUNDERSTORM:
                break;
            case LIGHT_THUNDER_STORM:
                break;
            case HEAVY_THUNDER_STORM:
                break;
            case RAGGED_THUNDER_STORM:
                break;
            case THUNDERSTROM_LIGHT_DRIZZLE:
                break;
            case THUNDERSTROM_DRIZZLE:
                break;
            case THUNDERSTROM_HEAVY_DRIZZLE:
                break;
        }
    }

    private String title;
    private String message;

    private void setTitle(String title) {this.title = title;}

    private void setMessage(String message) {this.message = message;}

    protected String getTitle() {return title;}

    protected String getMessage() {return message;}


    public boolean isAlertNeedToBeShown(WeatherStore store){

        ArrayList<WeatherModel> weatherModelList = store.getDailyWeatherModelList();

        boolean isAlertNeeded = true;

        for (int i = 0; i < weatherModelList.size(); i++) {
            WeatherModel model = weatherModelList.get(i);

            isAlertNeeded = checkIsAlertRequired(model.getId().intValue());

        }


        return isAlertNeeded;
    }

    public boolean checkIsAlertRequired(int code) {

        boolean isAlertNeeded = true;


        switch (code){

            // EXTREME CONDITIONS 9XX
            case 900:
                setWeatherAlertMessage(ALERT_TYPE.TORNADO);
                break;
            case 901:
                setWeatherAlertMessage(ALERT_TYPE.TROPICAL_STORM);
                break;
            case 902:
                setWeatherAlertMessage(ALERT_TYPE.HURRICANE);
                break;
            case 903:
                setWeatherAlertMessage(ALERT_TYPE.LOW_TEMP);
                break;
            case 904:
                setWeatherAlertMessage(ALERT_TYPE.HIGH_TEMP);
                break;
            case 905:
                setWeatherAlertMessage(ALERT_TYPE.WINDY);
                break;
            case 906:
                setWeatherAlertMessage(ALERT_TYPE.HAIL);
                break;
            case 957:
                setWeatherAlertMessage(ALERT_TYPE.HIGH_WIND);
                break;
            case 958:
                setWeatherAlertMessage(ALERT_TYPE.GALE);
                break;
            case 959:
                setWeatherAlertMessage(ALERT_TYPE.SEVERE_GALE);
                break;
            case 960:
                setWeatherAlertMessage(ALERT_TYPE.STORM);
                break;
            case 961:
                setWeatherAlertMessage(ALERT_TYPE.VOILENT_STORM);
                break;
            case 962:
                setWeatherAlertMessage(ALERT_TYPE.HURRICANE);
                break;


            case 804:
                setWeatherAlertMessage(ALERT_TYPE.OVERCAST_CLOUDY);
                break;



            case 701:
                setWeatherAlertMessage(ALERT_TYPE.MIST);
                break;
            case 711:
                setWeatherAlertMessage(ALERT_TYPE.SMOKE);
                break;
            case 721:
                setWeatherAlertMessage(ALERT_TYPE.HAZE);
                break;
            case 731:
                setWeatherAlertMessage(ALERT_TYPE.SAND_DUST);
                break;
            case 741:
                setWeatherAlertMessage(ALERT_TYPE.FOG);
                break;
            case 751:
                setWeatherAlertMessage(ALERT_TYPE.SAND);
                break;
            case 761:
                setWeatherAlertMessage(ALERT_TYPE.DUST);
                break;
            case 762:
                setWeatherAlertMessage(ALERT_TYPE.VOLCANIC_ASH);
                break;
            case 771:
                setWeatherAlertMessage(ALERT_TYPE.SQUALLS);
                break;
            case 781:
                setWeatherAlertMessage(ALERT_TYPE.TORNADO);
                break;

            case 602:
                setWeatherAlertMessage(ALERT_TYPE.HEAVY_SNOW);
                break;
            case 616:
                setWeatherAlertMessage(ALERT_TYPE.RAIN_AND_SNOW);
                break;
            case 622:
                setWeatherAlertMessage(ALERT_TYPE.HEAVY_SHOWER_SNOW);
                break;



            case 502:
                setWeatherAlertMessage(ALERT_TYPE.HEAVY_INTENSITY_RAIN);
                break;
            case 503:
                setWeatherAlertMessage(ALERT_TYPE.VERY_HEAVY_RAIN);
                break;
            case 504:
                setWeatherAlertMessage(ALERT_TYPE.EXTREME_RAIN);
                break;
            case 511:
                setWeatherAlertMessage(ALERT_TYPE.FREEZING_RAIN);
                break;
            case 520:
                setWeatherAlertMessage(ALERT_TYPE.LIGHT_INTENSITY_SHOWER_RAIN);
                break;
            case 521:
                setWeatherAlertMessage(ALERT_TYPE.SHOWER_RAIN);
                break;
            case 522:
                setWeatherAlertMessage(ALERT_TYPE.HEAVY_INTENSITY_SHOWER_RAIN);
                break;



            case 300:
                setWeatherAlertMessage(ALERT_TYPE.LIGHT_INTENSITY_DRIZZLE);
                break;
            case 301:
                setWeatherAlertMessage(ALERT_TYPE.DRIZZLE);
                break;
            case 302:
                setWeatherAlertMessage(ALERT_TYPE.HEAVY_INTENSITY_DRIZZLE_RAIN);
                break;
            case 310:
                setWeatherAlertMessage(ALERT_TYPE.LIGHT_INTENSITY_DRIZZLE_RAIN);
                break;
            case 311:
                setWeatherAlertMessage(ALERT_TYPE.DRIZZLE);
                break;
            case 312:
                setWeatherAlertMessage(ALERT_TYPE.HEAVY_INTENSITY_DRIZZLE_RAIN);
                break;
            case 313:
                setWeatherAlertMessage(ALERT_TYPE.SHOWER_RAIN_AND_DRIZZLE);
                break;
            case 314:
                setWeatherAlertMessage(ALERT_TYPE.HEAVY_SHOWER_RAIN_AND_DRIZZLE);
                break;
            case 321:
                setWeatherAlertMessage(ALERT_TYPE.SHOWER_DRIZZLE);
                break;



            case 200:
                setWeatherAlertMessage(ALERT_TYPE.THUNDERSTORM_LIGHT_RAIN);
                break;
            case 201:
                setWeatherAlertMessage(ALERT_TYPE.THUNDERSTORM_WITH_RAIN);
                break;
            case 202:
                setWeatherAlertMessage(ALERT_TYPE.THUNDERSTORM_HEAVY_RAIN);
                break;
            case 210:
                setWeatherAlertMessage(ALERT_TYPE.LIGHT_THUNDER_STORM);
                break;
            case 211:
                setWeatherAlertMessage(ALERT_TYPE.THUNDERSTORM);
                break;
            case 212:
                setWeatherAlertMessage(ALERT_TYPE.HEAVY_THUNDER_STORM);
                break;
            case 221:
                setWeatherAlertMessage(ALERT_TYPE.RAGGED_THUNDER_STORM);
                break;
            case 230:
                setWeatherAlertMessage(ALERT_TYPE.THUNDERSTROM_LIGHT_DRIZZLE);
                break;
            case 231:
                setWeatherAlertMessage(ALERT_TYPE.THUNDERSTROM_DRIZZLE);
                break;
            case 232:
                setWeatherAlertMessage(ALERT_TYPE.THUNDERSTROM_HEAVY_DRIZZLE);
                break;

            default:
                isAlertNeeded = false;
        }

        return isAlertNeeded;
    }


}
