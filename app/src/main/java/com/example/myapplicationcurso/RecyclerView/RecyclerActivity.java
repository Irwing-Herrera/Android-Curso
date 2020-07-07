package com.example.myapplicationcurso.RecyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.myapplicationcurso.Adapters.MyAdapterRecyclerView;
import com.example.myapplicationcurso.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity implements MyAdapterRecyclerView.OnItemClickListener {

    private List<String> names;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int _counter = 0;

    @Override
    public void OnItemClick(String name, int position) {
        _deleteName(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        names = this.getAllNames();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);

        // Cambiar a gridView y con dos columnas
        layoutManager = new GridLayoutManager(this, 2);

        adapter = new MyAdapterRecyclerView(names, R.layout.recycler_view_item, this);

        // Activar solo si se sabe que el contenedor no a a cambiar de tamaño (performance)
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                this._addName(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<String> getAllNames() {
        return new ArrayList<String>() {{
            add("Irwing");
            add("Santiago");
            add("Allison");
            add("Alexis");
        }};
    }

    private void _addName(int position) {
        names.add(position, "New Name " + (++_counter));
        adapter.notifyItemInserted(position);
        layoutManager.scrollToPosition(position);
    }

    private void _deleteName(int position) {
        names.remove(position);
        adapter.notifyItemRemoved(position);
    }
}