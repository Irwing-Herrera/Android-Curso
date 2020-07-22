package com.example.myapplicationcurso.Services.API;

import com.example.myapplicationcurso.Services.Deserializers.MyDeserializer;
import com.example.myapplicationcurso.Services.Models.City;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String KEY = "62315fdddd2d10bbfe93469b4a527b2f";

    private static Retrofit retrofit = null;

    public static Retrofit getApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }

    public static Retrofit getApiWithDeserializer() {
        if (retrofit == null) {

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(City.class, new MyDeserializer());

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }
        return  retrofit;
    }
}
