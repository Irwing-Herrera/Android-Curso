package com.example.myapplicationcurso.CardView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplicationcurso.R;

public class CardViewActivity extends AppCompatActivity {

    private TextView _txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        _txtView = findViewById(R.id.textView);
        _txtView.setText("Hello in Card View :D");
    }
}