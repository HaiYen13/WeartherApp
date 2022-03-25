package com.yenvth.myweather.models.currentweather;

import java.util.List;

public class CityWeatherModel {
    private String message;
    private String cod;
    private int count;

    private List<WeatherModel> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<WeatherModel> getList() {
        return list;
    }

    public void setList(List<WeatherModel> list) {
        this.list = list;
    }
}
