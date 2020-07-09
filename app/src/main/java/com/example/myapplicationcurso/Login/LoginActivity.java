package com.example.myapplicationcurso.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.myapplicationcurso.MainActivity;
import com.example.myapplicationcurso.R;
import com.example.myapplicationcurso.Utils.Utils;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    private EditText txtEmail;
    private EditText txtPassword;
    private Switch switchRemember;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _bindUI();

        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        _setCredentialsIfExist();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if(_login(email, password)) {
                    _goToMainActivity();
                    _saveOnPreferences(email, password);
                }
            }
        });
    }

    private void _saveOnPreferences(String email, String password) {
        if (switchRemember.isChecked()) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            editor.apply();
        }
    }

    private void _goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void _bindUI() {
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        switchRemember = findViewById(R.id.switchRemember);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private boolean _login(String email, String password) {
        if (!_isValidEmail(email)) {
            Toast.makeText(this, "Email is not valid, please try again", Toast.LENGTH_LONG).show();
            return false;
        } else if (!_isValidPassword(password)) {
            Toast.makeText(this, "Password is not valid, 4 characters or more ,please try again", Toast.LENGTH_LONG).show();
            return false;
        } else
            return true;
    }

    private boolean _isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean _isValidPassword(String password) {
        return password.length() > 4;
    }

    private void _setCredentialsIfExist() {
        String email = Utils._getUserEmailPreferences(preferences);
        String password = Utils._getUserPasswordPreferences(preferences);

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            txtEmail.setText(email);
            txtPassword.setText(password);
        }
    }
}