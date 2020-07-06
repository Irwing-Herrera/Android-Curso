package com.example.myapplicationcurso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplicationcurso.RecyclerView.RecyclerActivity;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private Button btnToast;
    private Button btnViewThird;
    private Button btnGridView;
    private Button btnViewRecyclerView;

    private ListView listViewDatos;
    private final String TEXTFROMFIRSTVIEW = "Hola desde primera vista";
    private List<String> nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Intent intent = new Intent(ListActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        btnGridView = findViewById(R.id.btnViewGridActivity);
        btnGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, GridActivity.class);
                startActivity(intent);
            }
        });

        btnViewRecyclerView = findViewById(R.id.btnViewRecyclerView);
        btnViewRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });

        listViewDatos = findViewById(R.id.listDatos);

        nombres = new ArrayList<String>();
        nombres.add("Irwing");
        nombres.add("Allison");
        nombres.add("Alexis");
        nombres.add("Gionathan");
        nombres.add("Charly");

        // Adaptador, la forma visual en que mostraremos nuestros datos
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres);
        // Enlazamos el adaptador con el List View
        // listViewDatos.setAdapter(adapter);

        listViewDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListActivity.this, "Seleccionado: " + nombres.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        // ENlazmos con nuestro adaptador perzonalizado
        MyAdpater myAdpater = new MyAdpater(this, R.layout.itemlayout, nombres);
        listViewDatos.setAdapter(myAdpater);
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
}

