package com.yenvth.myweather.models;

import java.util.List;

public class WeatherModel {
    private long id;
    private String name;
    private CoordModel coord;
    private MainModel main;
    private long dt;
    private WindModel wind;
    private SysModel sys;
    private int rain;
    private int snow;
    private CloudsModel clouds;
    private List<WeatherChildModel> weather;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoordModel getCoord() {
        return coord;
    }

    public void setCoord(CoordModel coord) {
        this.coord = coord;
    }

    public MainModel getMain() {
        return main;
    }

    public void setMain(MainModel main) {
        this.main = main;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public WindModel getWind() {
        return wind;
    }

    public void setWind(WindModel wind) {
        this.wind = wind;
    }

    public SysModel getSys() {
        return sys;
    }

    public void setSys(SysModel sys) {
        this.sys = sys;
    }

    public int getRain() {
        return rain;
    }

    public void setRain(int rain) {
        this.rain = rain;
    }

    public int getSnow() {
        return snow;
    }

    public void setSnow(int snow) {
        this.snow = snow;
    }

    public CloudsModel getClouds() {
        return clouds;
    }

    public void setClouds(CloudsModel clouds) {
        this.clouds = clouds;
    }

    public List<WeatherChildModel> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherChildModel> weather) {
        this.weather = weather;
    }
}
