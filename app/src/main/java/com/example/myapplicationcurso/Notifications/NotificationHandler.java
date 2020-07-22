package com.example.myapplicationcurso.Notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;

public class NotificationHandler extends ContextWrapper {

    private NotificationManager manager;
    private final String CHANNEL_HIGH_NAME = "HIGH CHANNEL";
    private final String CHANNEL_LOW_NAME = "LOW CHANNEL";

    public static final String CHANNEL_HIGH_ID = "1";
    public static final String CHANNEL_LOW_ID = "2";

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

    private Notification.Builder createNotificationWithChannel(String title, String message, String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new Notification.Builder(getApplicationContext(), channelId)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(android.R.drawable.stat_notify_chat)
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
