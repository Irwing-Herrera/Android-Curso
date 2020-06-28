package com.example.myapplicationcurso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textFirstView);

        // Tomar valores del intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getString("textView") != null) {
            String textFirstView = bundle.getString("textView");
            textView.setText(textFirstView);
        } else {
            textView.setText("Valor viene null");
        }
    }
}