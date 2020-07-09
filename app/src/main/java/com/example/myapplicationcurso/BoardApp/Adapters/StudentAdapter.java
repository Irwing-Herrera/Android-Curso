package com.example.myapplicationcurso.BoardApp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplicationcurso.BoardApp.Models.Alumno;
import com.example.myapplicationcurso.BoardApp.Models.Escuela;
import com.example.myapplicationcurso.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class StudentAdapter extends BaseAdapter {

    private Context context;
    private List<Alumno> alumnos;
    private int layout;

    public StudentAdapter(Context context, List<Alumno> alumnos, int layout) {
        this.context = context;
        this.alumnos = alumnos;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return alumnos.size();
    }

    @Override
    public Object getItem(int position) {
        return alumnos.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StudentAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            viewHolder = new StudentAdapter.ViewHolder();

            viewHolder.name = convertView.findViewById(R.id.txtName);
            viewHolder.lastName = convertView.findViewById(R.id.txtLastName);
            viewHolder.date = convertView.findViewById(R.id.txtDateIngreso);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (StudentAdapter.ViewHolder) convertView.getTag();
        }

        Alumno alumno = alumnos.get(position);

        viewHolder.name.setText(alumno.getNombre());
        viewHolder.lastName.setText(alumno.getApellido());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(alumno.getFechaIngreso());
        viewHolder.date.setText(date);

        return convertView;
    }

    public class ViewHolder {
        TextView name;
        TextView lastName;
        TextView date;
    }
}
