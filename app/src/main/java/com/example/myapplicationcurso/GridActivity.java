package com.example.myapplicationcurso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private GridView gridView;
    private MyAdpater myAdpater;
    private int counter = 0;
    // Listado de Nomnbres
    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        gridView = (GridView) findViewById(R.id.gridView);

        // Datos a mostrar
        names = new ArrayList<String>();
        names.add("Irwing");
        names.add("Santiago");
        names.add("Allison");
        names.add("Alexis");
        names.add("Gionathan");
        names.add("Charly");


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridActivity.this, "Seleccionado: " + names.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        // Enlazamos con nuestro adaptador personalizadp
        myAdpater = new MyAdpater(this, R.layout.grid_item, names);
        gridView.setAdapter(myAdpater);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                //Añadimos nuevo nombre
                this.names.add("Added n°" + (++counter));
                // Notificamos al adaptador del cambio
                this.myAdpater.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}