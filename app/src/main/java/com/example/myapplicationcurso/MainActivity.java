package com.example.myapplicationcurso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

public class MainActivity extends AppCompatActivity {

    private Button btnToast;
    private Button btnViewThird;
    private final String TEXTFROMFIRSTVIEW = "Hola desde primera vista";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();
        System.out.println("onCreate");

        btnToast = findViewById(R.id.btnToast);
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(v);
            }
        });

        btnViewThird = findViewById(R.id.btnViewThird);
        btnViewThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(View view) {
        /* Toast Whit library
        SuperActivityToast.create(this, new Style(), Style.TYPE_BUTTON)
                .setButtonText("onResume")
                .setOnButtonClickListener("good_tag_name", null, null)
                .setProgressBarColor(Color.WHITE)
                .setText("Ciclo de vida Android")
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_BLUE))
                .setAnimations(Style.ANIMATIONS_POP).show();*/

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("textView", TEXTFROMFIRSTVIEW);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");
        Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
        //Toast.makeText(this, "", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPause() {
        System.out.println("onPause");
        Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
        Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
        Toast.makeText(this, "onRestart", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
    }
}