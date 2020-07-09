package com.example.myapplicationcurso.BoardApp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplicationcurso.BoardApp.Adapters.EscuelaAdapter;
import com.example.myapplicationcurso.BoardApp.Adapters.StudentAdapter;
import com.example.myapplicationcurso.BoardApp.Models.Alumno;
import com.example.myapplicationcurso.BoardApp.Models.Escuela;
import com.example.myapplicationcurso.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

public class StudentActivity extends AppCompatActivity implements RealmChangeListener<Escuela> {

    private ListView listView;
    private FloatingActionButton floatingActionButton;

    private Realm realm;
    private StudentAdapter adapter;
    private RealmList<Alumno> students;

    private int schoolId;
    private Escuela school;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        realm = Realm.getDefaultInstance();

        if (getIntent().getExtras() != null)
            schoolId = getIntent().getExtras().getInt("id");

        school = realm.where(Escuela.class).equalTo("id", schoolId).findFirst();
        school.addChangeListener(this);
        students = school.getAlumnos();

        this.setTitle(school.getInstitucion());

        listView = findViewById(R.id.listViewStudents);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        adapter = new StudentAdapter(this, students, R.layout.list_view_student_item);

        listView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _showAlertForCreatingStudent("Alta de Alumno", "Ingresa el nombre y apellido del Alumno que va a estar en la escuela: " + school.getInstitucion() + ".");
            }
        });
    }

    private void _showAlertForCreatingStudent(String title, String message ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_student, null);
        builder.setView(viewInflated);

        final EditText txtName = viewInflated.findViewById(R.id.txtName);
        final EditText txtLastName = viewInflated.findViewById(R.id.txtLastName);

        builder.setPositiveButton("Add Student", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = txtName.getText().toString().trim();
                String lastName = txtLastName.getText().toString().trim();

                if (name.length() > 0 && lastName.length() > 0)
                    _createNewStudent(name, lastName);
                else
                    Toast.makeText(getApplicationContext(), "Fill the two inputs.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    private void _createNewStudent(String name, String lastName) {
        final Alumno student = new Alumno(name, lastName);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(student);
                school.getAlumnos().add(student);
            }
        });
    }

    @Override
    public void onChange(Escuela escuela) {
        adapter.notifyDataSetChanged();
    }
}