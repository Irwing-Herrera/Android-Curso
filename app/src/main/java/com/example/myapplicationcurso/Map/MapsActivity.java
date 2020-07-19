package com.example.myapplicationcurso.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplicationcurso.Map.Fragments.MapFragment;
import com.example.myapplicationcurso.Map.Fragments.WelcomeFragment;
import com.example.myapplicationcurso.R;

public class MapsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        _getUI();
        _getToolbar();

        if (savedInstanceState == null) {
            currentFragment = new WelcomeFragment();
            changeFrafment(currentFragment);
        }
    }

    private void _getUI() {
        toolbar = findViewById(R.id.toolbar);
    }

    private void _getToolbar() {
        toolbar.setTitle("Google Maps");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_welcome:
                currentFragment = new WelcomeFragment();
                break;
            case R.id.menu_go_map:
                currentFragment = new MapFragment();
                break;
        }
        changeFrafment(currentFragment);

        return super.onOptionsItemSelected(item);
    }

    private void changeFrafment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }
}