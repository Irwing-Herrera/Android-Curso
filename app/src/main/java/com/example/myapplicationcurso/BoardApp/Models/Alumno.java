package com.example.myapplicationcurso.BoardApp.Models;

import com.example.myapplicationcurso.MyApplication;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Alumno extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String nombre;
    @Required
    private String apellido;
    @Required
    private Date fechaIngreso;

    public Alumno() {};

    public Alumno(String nombre, String apellido) {
        this.id = MyApplication.AlumnoID.incrementAndGet();
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaIngreso = new Date();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }
}
