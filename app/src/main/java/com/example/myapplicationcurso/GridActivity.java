package com.example.myapplicationcurso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
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

        // registra context menu
        registerForContextMenu(gridView);
    }

    // Inflamos el layout de menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    // Seleccionames una opcion del menu de opciones
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

    // Inflamos el layout del context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();

        AdapterView.AdapterContextMenuInfo seleccionado = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.names.get(seleccionado.position));

        inflater.inflate(R.menu.context_menu, menu);
    }

    // Manejamos eventos click en el context menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo seleccionado = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.delete_item:
                this.names.remove(seleccionado.position);
                this.myAdpater.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}