package com.example.myapplicationcurso.Notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Build;

import com.example.myapplicationcurso.Notifications.Activities.NotificationActivity;
import com.example.myapplicationcurso.R;

public class NotificationHandler extends ContextWrapper {

    private NotificationManager manager;
    private final String CHANNEL_HIGH_NAME = "HIGH CHANNEL";
    private final String CHANNEL_LOW_NAME = "LOW CHANNEL";

    public static final String CHANNEL_HIGH_ID = "1";
    public static final String CHANNEL_LOW_ID = "2";

    private final int SUMMARY_GROUP_ID = 1001; // es un nuemro random
    private final String SUMMARY_GROUP_NAME = "GROUPING_NOTIFICATION";

    private void createChannels() {

        if (Build.VERSION.SDK_INT >= 26) {
            // Creating High Channel
            NotificationChannel highChannel = new NotificationChannel(
                    CHANNEL_HIGH_ID,
                    CHANNEL_HIGH_NAME,
                    NotificationManager.IMPORTANCE_HIGH
            );

            // ... Extra Config ...
            highChannel.enableLights(true); // Encender led
            highChannel.setLightColor(Color.BLUE); // Color de led
            highChannel.setShowBadge(true); // Mostrar circulo en acceso directo de app
            highChannel.enableVibration(true); // Habilitar vibracion
            highChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC); // Visibilidad de notificacion con celular bloqueado

            // Creating Low Channel
            NotificationChannel lowChannel = new NotificationChannel(
                    CHANNEL_LOW_ID,
                    CHANNEL_LOW_NAME,
                    NotificationManager.IMPORTANCE_LOW
            );

            getManager().createNotificationChannel(highChannel);
            getManager().createNotificationChannel(lowChannel);
        }

    }

    private Notification.Builder createNotificationWithChannel(String title, String message, String channelId)  {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Intent intent = new Intent(this, NotificationActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("message", message);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

            Notification.Action action = new Notification.Action.Builder(
                    Icon.createWithResource(this, android.R.drawable.ic_menu_send),
                    "See Details",
                    pendingIntent).build();

            return new Notification.Builder(getApplicationContext(), channelId)
                    .setContentTitle(title)
                    .setContentText(message)
                    .addAction(action)
                    //.setContentIntent(pendingIntent)
                    .setColor(getColor(R.color.colorPrimary))
                    .setSmallIcon(android.R.drawable.stat_notify_chat)
                    .setGroup(SUMMARY_GROUP_NAME)
                    .setAutoCancel(true);
        }

        return null;
    }

    private Notification.Builder createNotificationWithoutChannel(String title, String message) {
        return new Notification.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setAutoCancel(true);
    }

    public void publishNotificationSummaryGroup(boolean isHighImportance) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelID = (isHighImportance) ? CHANNEL_HIGH_ID : CHANNEL_LOW_ID;
            Notification summaryNotification = new Notification.Builder(getApplicationContext(), channelID)
                    .setSmallIcon(android.R.drawable.stat_notify_call_mute)
                    .setGroup(SUMMARY_GROUP_NAME)
                    .setGroupSummary(true)
                    .build();
            getManager().notify(SUMMARY_GROUP_ID, summaryNotification);
        }
    }

    public NotificationHandler(Context base) {
        super(base);
        createChannels();
    }

    public NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return manager;
    }

    public Notification.Builder createNotification(String title, String message, boolean isHighImportance) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (isHighImportance) {
                return this.createNotificationWithChannel(title, message, CHANNEL_HIGH_ID);
            }
            return this.createNotificationWithChannel(title, message, CHANNEL_LOW_ID);
        }
        return this.createNotificationWithoutChannel(title, message);
    }

}
