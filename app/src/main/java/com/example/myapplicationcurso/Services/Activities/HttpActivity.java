package com.example.myapplicationcurso.Services.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplicationcurso.R;
import com.example.myapplicationcurso.Services.API.API;
import com.example.myapplicationcurso.Services.API.APIServices.WeatherService;
import com.example.myapplicationcurso.Services.Models.City;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        _getUI();
        _getToolbar();

        _getCity("Mexico");

    }

    private void _getUI() {
        toolbar = findViewById(R.id.toolbar);
    }

    private void _getToolbar() {
        toolbar.setTitle("HTTP Services");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void _getCity(String mexico) {

        WeatherService service = API.getApiWithDeserializer().create(WeatherService.class);

        Call<City> cityCall = service.getCityCelsius("Mexico", API.KEY, "metric");
        cityCall.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                City city = response.body();
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Toast.makeText(HttpActivity.this, "Fail in request", Toast.LENGTH_LONG).show();
            }
        });

    }

}