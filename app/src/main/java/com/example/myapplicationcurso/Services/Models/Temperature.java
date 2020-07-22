package com.example.myapplicationcurso.Services.Models;

import com.google.gson.annotations.SerializedName;

public class Temperature {

    private float temp;
    @SerializedName("feels_like")
    private float feelsLike;
    @SerializedName("temp_min")
    private float tempMin;
    @SerializedName("temp_max")
    private float tempMax;
    private float pressure;
    private float humidity;

    public Temperature() {}

    public Temperature(float temp, float feelsLike, float tempMin, float tempMax, float pressure, float humidity) {
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(float feelsLike) {
        this.feelsLike = feelsLike;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
