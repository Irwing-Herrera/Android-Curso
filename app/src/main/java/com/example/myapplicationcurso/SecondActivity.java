package com.example.myapplicationcurso;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textFirstView);
        btnResult = findViewById(R.id.btnResult);

        // Tomar valores del intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getString("textView") != null) {
            String textFirstView = bundle.getString("textView");
            textView.setText(textFirstView);
        } else {
            textView.setText("Valor viene null");
        }

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("resultado", 11);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}