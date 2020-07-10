package com.example.myapplicationcurso.MyFragments.Activities;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.myapplicationcurso.MyFragments.Fragments.DataFragment;
import com.example.myapplicationcurso.MyFragments.Fragments.DetailsFragment;
import com.example.myapplicationcurso.R;

public class MyFragmentsActivity extends FragmentActivity implements DataFragment.DataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
    }

    @Override
    public void sendData(String text) {
        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        detailsFragment._renderText(text);
    }
}