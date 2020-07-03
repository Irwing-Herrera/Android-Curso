package com.example.myapplicationcurso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdpater extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> names;

    public  MyAdpater(Context context, int layout, List<String> names) {
        this.context = context;
        this.layout = layout;
        this.names = names;
    }

    // las veces que va iterar
    @Override
    public int getCount() {
        return this.names.size();
    }

    // obtener item de coleccion
    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    // obtener id de item de la coleccion
    @Override
    public long getItemId(int position) {
        return position;
    }

    // obtiene cada item y dibuja
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Copiamos la vista
        View view = convertView;

        // Vinculamos con el layout Item
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.itemlayout, null);

        // extraemos nombre de cada posicion
        String currentName = names.get(position);

        // Agregamos nombre a variable de UI
        TextView textViewNombre = view.findViewById(R.id.txtNombre);
        textViewNombre.setText(currentName);

        return view;
    }
}
