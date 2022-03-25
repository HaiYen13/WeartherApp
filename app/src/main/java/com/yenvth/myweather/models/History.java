package com.yenvth.myweather.models;

import androidx.annotation.NonNull;

public class History {
    private String name_city;
    private String date;
    private String time;
    private String img;
    private String description;
    private float temp;
    private int pressure;
    private int humidity;

    public History(String name_city, String date, String time, String img, String description, float temp, int pressure, int humidity) {
        this.name_city = name_city;
        this.date = date;
        this.time = time;
        this.img = img;
        this.description = description;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public String getName_city() {
        return name_city;
    }

    public void setName_city(String name_city) {
        this.name_city = name_city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "History{" +
                "name_city='" + name_city + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
