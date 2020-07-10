package com.example.myapplicationcurso.MyFragments.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplicationcurso.R;

public class DataFragment extends Fragment {

    private EditText editTextData;
    private Button buttonData;

    private DataListener callback;

    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            callback = (DataListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " should implement DataListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_data, container, false);
        _getUI(view);
        
        buttonData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _sendText(editTextData.getText().toString());
            }
        });

        return view;
    }

    private void _sendText(String text) {
        callback.sendData(text);
    }

    private void _getUI(View view) {
        editTextData = view.findViewById(R.id.editTextData);
        buttonData = view.findViewById(R.id.buttonData);
    }

    public interface DataListener {
        void sendData(String text);
    }
}