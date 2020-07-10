package com.example.myapplicationcurso.MyFragments.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplicationcurso.R;

public class DetailsFragment extends Fragment {

    private TextView textViewDetails;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        _getUI(view);

        return view;
    }

    private void _getUI(View view) {
        textViewDetails = view.findViewById(R.id.textViewDetails);
    }

    public void _renderText(String text) {
        textViewDetails.setText(text);
    }
}