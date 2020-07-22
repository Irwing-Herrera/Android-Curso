package com.example.myapplicationcurso.Notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.myapplicationcurso.R;

public class NotificationActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        _getUI();
        _getToolbar();

    }

    private void _getUI() {
        toolbar = findViewById(R.id.toolbar);
    }

    private void _getToolbar() {
        toolbar.setTitle("Notificaciones");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}