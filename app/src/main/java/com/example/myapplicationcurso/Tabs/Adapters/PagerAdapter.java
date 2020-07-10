package com.example.myapplicationcurso.Tabs.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplicationcurso.Tabs.Fragments.FirstFragment;
import com.example.myapplicationcurso.Tabs.Fragments.SecondFragment;
import com.example.myapplicationcurso.Tabs.Fragments.ThirdFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int numberOfTabs) {
        super(fm, numberOfTabs);
        this.numberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
