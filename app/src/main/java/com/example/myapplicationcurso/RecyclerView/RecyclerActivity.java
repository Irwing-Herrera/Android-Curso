package com.example.myapplicationcurso.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplicationcurso.Adapters.MyAdapterRecyclerView;
import com.example.myapplicationcurso.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private List<String> names;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        names = this.getAllNames();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);

        adapter = new MyAdapterRecyclerView(names, R.layout.recycler_view_item, new MyAdapterRecyclerView.OnItemClickListener() {
            @Override
            public void OnItemClick(String name, int position) {
                Toast.makeText(RecyclerActivity.this, "Seleccionado:" + name, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private List<String> getAllNames() {
        return new ArrayList<String>() {{
            add("Irwing");
            add("Santiago");
            add("Allison");
            add("Alexis");
        }};
    }
}