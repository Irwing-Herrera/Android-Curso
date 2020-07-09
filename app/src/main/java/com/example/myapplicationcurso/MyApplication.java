package com.example.myapplicationcurso;

import android.app.Application;

import com.example.myapplicationcurso.BoardApp.Models.Alumno;
import com.example.myapplicationcurso.BoardApp.Models.Escuela;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApplication extends Application {

    public static AtomicInteger AlumnoID = new AtomicInteger();
    public static AtomicInteger EscuelaID = new AtomicInteger();

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        _setUpRealmConfig();
        Realm realm = Realm.getDefaultInstance();

        AlumnoID = _getIdByTable(realm, Alumno.class);
        EscuelaID = _getIdByTable(realm, Escuela.class);

        realm.close();
    }

    private void _setUpRealmConfig() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("defaultEscuela.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger _getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}
