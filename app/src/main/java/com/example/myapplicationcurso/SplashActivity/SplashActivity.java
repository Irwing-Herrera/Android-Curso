package com.example.myapplicationcurso.SplashActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.myapplicationcurso.Login.LoginActivity;
import com.example.myapplicationcurso.MainActivity;
import com.example.myapplicationcurso.Utils.Utils;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        Intent intentLogin = new Intent(this, LoginActivity.class);
        Intent intentMain = new Intent(this, MainActivity.class);

        if (!TextUtils.isEmpty(Utils._getUserEmailPreferences(preferences)) && !TextUtils.isEmpty(Utils._getUserPasswordPreferences(preferences))) {
            startActivity(intentMain);
        } else {
            startActivity(intentLogin);
        }

        // Mata la activity actual
        finish();
    }


}