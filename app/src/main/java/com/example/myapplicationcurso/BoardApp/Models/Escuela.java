package com.example.myapplicationcurso.BoardApp.Models;

import com.example.myapplicationcurso.MyApplication;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Escuela extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String institucion;
    private RealmList<Alumno> alumnos;

    public Escuela() {};

    public Escuela(String institucion) {
        this.id = MyApplication.EscuelaID.incrementAndGet();
        this.institucion = institucion;
        this.alumnos = new RealmList<Alumno>();
    }

    public int getId() {
        return id;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public RealmList<Alumno> getAlumnos() {
        return alumnos;
    }

}
