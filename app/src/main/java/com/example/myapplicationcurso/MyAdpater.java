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

        // View Holder Pattern
        ViewHolder holder;

        // se hace para no buscar en cada iteracion el txtNombre en R
        if (convertView == null) {
            // Vinculamos con el layout Item
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.itemlayout, null);

            holder = new ViewHolder();
            // Agregamos nombre a variable de UI
            holder.nameTextView = convertView.findViewById(R.id.txtNombre);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // extraemos nombre de cada posicion
        String currentName = names.get(position);
        holder.nameTextView.setText(currentName);

        return convertView;
    }

    static class ViewHolder {
        private TextView nameTextView;
    }
}
