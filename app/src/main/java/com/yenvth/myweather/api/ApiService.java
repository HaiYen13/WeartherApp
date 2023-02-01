package com.yenvth.myweather.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yenvth.myweather.models.citylist.CityListModel;
import com.yenvth.myweather.models.currentweather.CityWeatherModel;
import com.yenvth.myweather.utils.Constant;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    static final String BASE_URL = Constant.baseUrl;
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("find")
    Call<CityWeatherModel> getCity(@Query("q") String data,
                                   @Query("units") String units,
                                   @Query("appid") String appId,
                                   @Query("lang") String lang);



}
