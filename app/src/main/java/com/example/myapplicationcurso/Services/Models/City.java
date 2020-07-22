package com.example.myapplicationcurso.Services.Models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class City {

    private int id;
    private String name;
    private String country;
    //@SerializedName("main")
    //private Temperature temperature;

    public City() {}

    public City(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
        //this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /*public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public static Temperature parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        Temperature temperature = gson.fromJson(response, Temperature.class);
        return  temperature;
    }*/
}
