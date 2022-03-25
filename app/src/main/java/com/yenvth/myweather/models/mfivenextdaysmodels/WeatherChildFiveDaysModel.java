package com.yenvth.myweather.models.mfivenextdaysmodels;

import java.util.List;

public class WeatherChildFiveDaysModel {
    private long dt;
    private TemperatureModel temp;
    private List<WeatherModel> weather;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public TemperatureModel getTemp() {
        return temp;
    }

    public void setTemp(TemperatureModel temp) {
        this.temp = temp;
    }

    public List<WeatherModel> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherModel> weather) {
        this.weather = weather;
    }
}
