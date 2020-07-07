package com.example.myapplicationcurso.RecyclerCardView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplicationcurso.Adapters.MyAdapterRecyclerView;
import com.example.myapplicationcurso.Models.Movie;
import com.example.myapplicationcurso.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerCardActivity extends AppCompatActivity implements MyAdapterRecyclerCardView.OnItemClickListener {

    private List<Movie> movies;
    private RecyclerView recyclerViewMovies;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    private int _counter = 0;

    @Override
    public void onItemClick(Movie movie, int position) {
        _removeMovie(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_card);

        movies = _getAllMovies();
        recyclerViewMovies = (RecyclerView) findViewById(R.id.recyclerViewMovies);
        myLayoutManager = new LinearLayoutManager(this);

        myAdapter = new MyAdapterRecyclerCardView(movies, R.layout.recycler_card_view, this);

        recyclerViewMovies.setLayoutManager(myLayoutManager);
        recyclerViewMovies.setAdapter(myAdapter);
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
                this._addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    private void _removeMovie(int position) {
        movies.remove(position);
        myAdapter.notifyItemRemoved(position);
    }

    private void _addMovie(int position) {
        movies.add(position, new Movie("New Movie " + ++_counter, R.drawable.img));
        myAdapter.notifyItemInserted(position);
        myLayoutManager.scrollToPosition(position);
    }

}