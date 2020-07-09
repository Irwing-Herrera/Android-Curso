package com.example.myapplicationcurso.ListView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplicationcurso.MainActivity;
import com.example.myapplicationcurso.MyAdpater;
import com.example.myapplicationcurso.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private ListView listViewDatos;
    private List<String> nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listViewDatos = findViewById(R.id.listDatos);

        _getNamesAll();
        // Adaptador, la forma visual en que mostraremos nuestros datos
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres);
        // Enlazamos el adaptador con el List View
        // listViewDatos.setAdapter(adapter);

        listViewDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, "Seleccionado: " + nombres.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        // ENlazmos con nuestro adaptador perzonalizado
        MyAdpater myAdpater = new MyAdpater(this, R.layout.itemlayout, nombres);
        listViewDatos.setAdapter(myAdpater);
    }

    private void _getNamesAll() {
        nombres = new ArrayList<String>();
        nombres.add("Irwing");
        nombres.add("Allison");
        nombres.add("Alexis");
        nombres.add("Gionathan");
        nombres.add("Charly");

    }
}