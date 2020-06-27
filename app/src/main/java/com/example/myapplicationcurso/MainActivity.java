package com.example.myapplicationcurso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();
        System.out.println("onCreate");
    }

    @Override protected void onStart() {
        super.onStart();
        System.out.println("onStart");
        Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show();
    }

    @Override protected void onResume() {
        super.onResume();
        System.out.println("onResume");
        Toast.makeText(this, "onResume", Toast.LENGTH_LONG).show();
    }

    @Override protected void onPause() {
        System.out.println("onPause");
        Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show();
        super.onPause();
    }

    @Override protected void onStop() {
        super.onStop();
        System.out.println("onStop");
        Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
    }

    @Override protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
        Toast.makeText(this, "onRestart", Toast.LENGTH_LONG).show();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
    }
}