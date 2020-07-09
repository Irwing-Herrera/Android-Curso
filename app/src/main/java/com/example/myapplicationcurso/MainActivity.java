package com.example.myapplicationcurso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplicationcurso.BoardApp.Activities.BoardActivity;
import com.example.myapplicationcurso.CardView.CardViewActivity;
import com.example.myapplicationcurso.ListView.ListViewActivity;
import com.example.myapplicationcurso.Login.LoginActivity;
import com.example.myapplicationcurso.RecyclerCardView.RecyclerCardActivity;
import com.example.myapplicationcurso.RecyclerView.RecyclerActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    private Button btnToast;
    private Button btnViewThird;
    private Button btnGridView;
    private Button btnViewRecyclerView;
    private Button btnCardView;
    private Button btnViewRecyclerCardView;
    private Button btnListView;
    private Button btnRealm;

    private final String TEXTFROMFIRSTVIEW = "Hola desde primera vista";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        // agregar icon a actionBar
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

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

        btnGridView = findViewById(R.id.btnViewGridActivity);
        btnGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GridActivity.class);
                startActivity(intent);
            }
        });

        btnViewRecyclerView = findViewById(R.id.btnViewRecyclerView);
        btnViewRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });

        btnCardView = findViewById(R.id.btnViewCardView);
        btnCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CardViewActivity.class);
                startActivity(intent);
            }
        });

        btnViewRecyclerCardView = findViewById(R.id.btnViewRecyclerCardView);
        btnViewRecyclerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerCardActivity.class);
                startActivity(intent);
            }
        });

        btnListView = findViewById(R.id.btnListView);
        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

        btnRealm = findViewById(R.id.btnRealm);
        btnRealm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BoardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("textView", TEXTFROMFIRSTVIEW);
        startActivityForResult(intent,1);
    }

    private void _logOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void _removeSharedPreferences() {
        preferences.edit().clear().apply();
    }

    // Crear menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // Click Listener de seleccion de opcion de menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                _logOut();
                return true;
            case R.id.menu_forget_logout:
                _removeSharedPreferences();
                _logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");
        // Respuesta en consola filtrado por etiqueta
        // Entrar a la pestaña de Logcat
        //   Seleccionar Info
        //     Filtrar por etiqueta
        Log.i("consola","Entre al onStart");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Codigo de resultado" + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            int info = data.getIntExtra("resultado", 0);
            System.out.println("El resultado es: " + info);
        }
    }
}

