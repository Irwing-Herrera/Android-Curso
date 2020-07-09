package com.example.myapplicationcurso.Utils;

import android.content.SharedPreferences;

public class Utils {

    public static String _getUserEmailPreferences(SharedPreferences preferences) {
        return  preferences.getString("email", "");
    }

    public static String _getUserPasswordPreferences(SharedPreferences preferences) {
        return  preferences.getString("password", "");
    }
}
