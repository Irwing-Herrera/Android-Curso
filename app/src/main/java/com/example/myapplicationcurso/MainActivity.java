package com.example.myapplicationcurso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplicationcurso.BoardApp.Activities.BoardActivity;
import com.example.myapplicationcurso.CardView.CardViewActivity;
import com.example.myapplicationcurso.DrawerNavigation.Fragments.AlertsFragment;
import com.example.myapplicationcurso.DrawerNavigation.Fragments.EmailFragment;
import com.example.myapplicationcurso.DrawerNavigation.Fragments.InfoFragment;
import com.example.myapplicationcurso.ListView.ListViewActivity;
import com.example.myapplicationcurso.Login.LoginActivity;
import com.example.myapplicationcurso.Map.MapsActivity;
import com.example.myapplicationcurso.MyFragments.Activities.MyFragmentsActivity;
import com.example.myapplicationcurso.Notifications.Activities.NotificationActivity;
import com.example.myapplicationcurso.RecyclerCardView.RecyclerCardActivity;
import com.example.myapplicationcurso.RecyclerView.RecyclerActivity;
import com.example.myapplicationcurso.Services.Activities.HttpActivity;
import com.example.myapplicationcurso.Tabs.Activities.TabsActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private Button btnToast;
    private Button btnViewThird;
    private Button btnGridView;
    private Button btnViewRecyclerView;
    private Button btnCardView;
    private Button btnViewRecyclerCardView;
    private Button btnListView;
    private Button btnRealm;
    private Button btnFragments;
    private Button btnTabs;
    private Button btnGoogleMaps;
    private Button btnHttpServices;
    private Button btnNotification;

    private final String TEXT_FROM_FIRST_VIEW = "Hola desde primera vista";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        _setToolbar();
        _setDrawerLayout();
        _setNavigationView();
        _setFragmentByDefault();

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

        btnFragments = findViewById(R.id.btnFragments);
        btnFragments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyFragmentsActivity.class);
                startActivity(intent);
            }
        });

        btnTabs = findViewById(R.id.btnTabs);
        btnTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TabsActivity.class);
                startActivity(intent);
            }
        });

        btnGoogleMaps = findViewById(R.id.btnGoogleMaps);
        btnGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        btnHttpServices = findViewById(R.id.btnHttpServices);
        btnHttpServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HttpActivity.class);
                startActivity(intent);
            }
        });

        btnNotification = findViewById(R.id.btnNotification);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void _setNavigationView() {
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.menu_email:
                        fragment = new EmailFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_alert:
                        fragment = new AlertsFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_info:
                        fragment = new InfoFragment();
                        fragmentTransaction = true;
                        break;
                }

                if (fragmentTransaction) {
                    _onOpenFramentWgithDreawer(fragment, item);
                    drawerLayout.closeDrawers();
                }

                return false;
            }
        });
    }

    private void _setFragmentByDefault() {
        _onOpenFramentWgithDreawer(new EmailFragment(), navigationView.getMenu().getItem(0));
    }

    private void _onOpenFramentWgithDreawer(Fragment fragment, MenuItem item ) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    private void _setDrawerLayout() {
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    private void _setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showToast(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("textView", TEXT_FROM_FIRST_VIEW);
        startActivityForResult(intent, 1);
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
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
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
        Log.i("consola", "Entre al onStart");
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

