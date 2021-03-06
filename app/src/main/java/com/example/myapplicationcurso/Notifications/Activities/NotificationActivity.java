package com.example.myapplicationcurso.Notifications.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Notification;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationcurso.Notifications.NotificationHandler;
import com.example.myapplicationcurso.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editTextTitle)
    EditText editTextTitle;
    @BindView(R.id.editTextMessage)
    EditText editTextMessage;
    @BindView(R.id.switchImportance)
    Switch switchImportance;

    @BindView(R.id.textViewIntentTitle)
    TextView textViewIntentTitle;
    @BindView(R.id.textViewIntentMessage)
    TextView textViewIntentMessage;

    private boolean isHighImportance = false;
    private NotificationHandler notificationHandler;

    private int _counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        _setIntentValues();

        notificationHandler = new NotificationHandler(this);
        _getToolbar();

    }

    @OnClick(R.id.buttonSend)
    public void click() {
        sendNotification();
    }

    @OnCheckedChanged(R.id.switchImportance)
    public void change(CompoundButton buttonView, boolean isChecked) {
        isHighImportance = isChecked;
    }

    private void sendNotification() {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)) {
            Notification.Builder builder = notificationHandler.createNotification(title,message,isHighImportance);
            notificationHandler.getManager().notify(++_counter,builder.build());
            notificationHandler.publishNotificationSummaryGroup(isHighImportance);
        }

    }

    private void _getToolbar() {
        toolbar.setTitle("Notificaciones");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void _setIntentValues() {
        if (getIntent() != null) {
            textViewIntentTitle.setText(getIntent().getStringExtra("title"));
            textViewIntentMessage.setText(getIntent().getStringExtra("message"));
        }
    }
}