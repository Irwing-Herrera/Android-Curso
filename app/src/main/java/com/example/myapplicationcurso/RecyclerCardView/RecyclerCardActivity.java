package com.example.myapplicationcurso.RecyclerCardView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplicationcurso.Models.Movie;
import com.example.myapplicationcurso.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerCardActivity extends AppCompatActivity {

    private List<Movie> movies;
    private RecyclerView recyclerViewMovies;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_card);

        movies = _getAllMovies();
        recyclerViewMovies = (RecyclerView) findViewById(R.id.recyclerViewMovies);
        myLayoutManager = new LinearLayoutManager(this);

        myAdapter = new MyAdapterRecyclerCardView(movies, R.layout.recycler_card_view, new MyAdapterRecyclerCardView.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {

            }
        });

        recyclerViewMovies.setLayoutManager(myLayoutManager);
        recyclerViewMovies.setAdapter(myAdapter);
    }

    private List<Movie> _getAllMovies() {
        return new ArrayList<Movie>() {{
            add(new Movie("Boku No Hero", R.drawable.img));
            add(new Movie("Boku No Hero 1", R.drawable.img));
            add(new Movie("Boku No Hero 2", R.drawable.img));
            add(new Movie("Boku No Hero 3", R.drawable.img));
            add(new Movie("Boku No Hero 4", R.drawable.img));
        }};
    }
}