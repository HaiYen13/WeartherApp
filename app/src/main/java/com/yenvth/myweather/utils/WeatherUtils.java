package com.yenvth.myweather.utils;

import com.yenvth.myweather.R;

public class WeatherUtils {
    public static int getIcon(String icon){
        switch (icon){
            case "01d" :
            case "01n" :
                return R.drawable.icon_clear_sky;
            case "02d" :
            case "02n" :
                return R.drawable.icon_few_clouds;
            case "03d" :
            case "03n" :
                return R.drawable.icon_scattered_clouds;
            case "04d" :
            case "04n" :
                return R.drawable.icon_broken_clouds;
            case "09d" :
            case "09n" :
                return R.drawable.icon_shower_rain;
            case "10d" :
            case "10n" :
                return R.drawable.icon_rain;
            case "11d" :
            case "11n" :
                return R.drawable.icon_thunderstorm;
            case "13d" :
            case "13n" :
                return R.drawable.icon_snow;
            case "50d" :
            case "50n" :
                return R.drawable.icon_mist1;
            default:
                return R.drawable.icon_broken_clouds;
        }
    }
}
