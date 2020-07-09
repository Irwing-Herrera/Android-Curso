package com.example.myapplicationcurso.BoardApp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplicationcurso.BoardApp.Adapters.EscuelaAdapter;
import com.example.myapplicationcurso.BoardApp.Models.Escuela;
import com.example.myapplicationcurso.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class BoardActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Escuela>>, AdapterView.OnItemClickListener {

    // Variables
    private Realm realm;

    private FloatingActionButton floatingActionButton;
    private ListView listView;
    private EscuelaAdapter adapter;

    private RealmResults<Escuela> escuelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        realm = Realm.getDefaultInstance();

        escuelas = realm.where(Escuela.class).findAll();
        escuelas.addChangeListener(this);

        adapter = new EscuelaAdapter(this, escuelas, R.layout.list_new_escuela);
        listView = findViewById(R.id.listViewEscuela);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _showAlertForCreatingSchool("Alta de Escuela", "Ingresa el nombre de la nueva Institucion.");
            }
        });

        registerForContextMenu(listView);
    }

    // crear menu de seleccion de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_school_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // establecer eventos de menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAll:
                _deleteAllSchool();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // crear context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(escuelas.get(info.position).getInstitucion());

        getMenuInflater().inflate(R.menu.context_menu_school_activity, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // seleccion de opcion de context menu
    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.editSchool:
                _showAlertForEditingSchool("Edit School", "Ingresa un nuevo nombre", escuelas.get(info.position));
                return true;
            case R.id.deleteSchool:
                _deleteSchool(escuelas.get(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    // mostrar alert de alta de escuela
    private void _showAlertForCreatingSchool(String title, String message ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_escuela, null);
        builder.setView(viewInflated);

        final EditText inputNameEscuela = viewInflated.findViewById(R.id.editTextEscuela);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String escuelaName = inputNameEscuela.getText().toString().trim();
                if (escuelaName.length() > 0)
                    createNewEscuela(escuelaName);
                else
                    Toast.makeText(getApplicationContext(), "Ingresa un nombre", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    // mostrar alert de editar escuela
    private void _showAlertForEditingSchool(String title, String message, final Escuela escuela ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_escuela, null);
        builder.setView(viewInflated);

        final EditText inputNameEscuela = viewInflated.findViewById(R.id.editTextEscuela);
        inputNameEscuela.setText(escuela.getInstitucion());

        builder.setPositiveButton("Edit School", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String escuelaName = inputNameEscuela.getText().toString().trim();

                if (escuelaName.length() == 0)
                    Toast.makeText(getApplicationContext(), "Ingresa un nombre", Toast.LENGTH_SHORT).show();
                else if (escuelaName.equals(escuela.getInstitucion()))
                    Toast.makeText(getApplicationContext(), "Tiene que ser diferente el nombre", Toast.LENGTH_SHORT).show();
                else
                    _editSchool(escuelaName, escuela);
            }
        });

        builder.create().show();
    }

    // notificar cuando se agrega una escuela
    @Override
    public void onChange(RealmResults<Escuela> escuelas) {
        adapter.notifyDataSetChanged();
    }

    // navegar a activity de alumnos
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(BoardActivity.this, StudentActivity.class);
        intent.putExtra("id", escuelas.get(position).getId());
        startActivity(intent);
    }

    // Crear nueva escuela
    private void createNewEscuela(String escuelaName) {
        final Escuela escuela = new Escuela(escuelaName);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(escuela);
            }
        });
    }

    // borrar escuela
    private void _deleteSchool(final Escuela school) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                school.deleteFromRealm();
            }
        });
    }

    // borrar todas las escuelas
    private void _deleteAllSchool() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }

    // editar escuela
    private void _editSchool(final String newName, final Escuela school) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                school.setInstitucion(newName);
                realm.copyToRealmOrUpdate(school);
            }
        });
    }
}