package com.example.myapplicationcurso.BoardApp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplicationcurso.BoardApp.Models.Escuela;
import com.example.myapplicationcurso.R;

import org.w3c.dom.Text;

import java.util.List;

public class EscuelaAdapter extends BaseAdapter {

    private Context context;
    private List<Escuela> escuelas;
    private int layout;

    public EscuelaAdapter(Context context, List<Escuela> escuelas, int layout) {
        this.context = context;
        this.escuelas = escuelas;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return escuelas.size();
    }

    @Override
    public Escuela getItem(int position) {
        return escuelas.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.nameSchool = convertView.findViewById(R.id.txtTitleEscuela);
            viewHolder.students = convertView.findViewById(R.id.txtEscuelaAlumnos);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Escuela escuela = escuelas.get(position);
        viewHolder.nameSchool.setText(escuela.getInstitucion());

        int numberOfStudents = escuela.getAlumnos().size();
        String textForStudents = (numberOfStudents == 1) ? numberOfStudents + " Student" : numberOfStudents + " Students";
        viewHolder.students.setText(textForStudents);

        return  convertView;
    }

    public class ViewHolder {
        TextView nameSchool;
        TextView students;
    }
}
