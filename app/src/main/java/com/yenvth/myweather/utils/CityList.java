package com.yenvth.myweather.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class CityList {
    public static String loadJsonFromAsset(Context context){
        String json = null;
        try{
            InputStream is =
                    context.getAssets().open("city.list.min.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
