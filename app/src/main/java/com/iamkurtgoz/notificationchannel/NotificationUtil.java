package com.iamkurtgoz.notificationchannel;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class NotificationUtil {

    private String CHANNEL_ID = "CHANNEL_ID";
    private String CHANNEL_NAME = "CHANNEL_NAME";

    private Context context;
    public static NotificationUtil with(Context context){
        return new NotificationUtil(context);
    }

    public NotificationUtil(Context context){
        this.context = context;
    }

    private NotificationManager getNotificationManager(){
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setShowBadge(true); //Bildirim geldiğinde uygulama ikonunda belirtmesi için
            channel.enableVibration(true); //bildirim titreşimi
            channel.enableLights(true); //Varsa bildirim ışığı
            manager.createNotificationChannel(channel);
        }
        return manager;
    }

    public void showNotification(String title, String text, int icon, Intent resultIntent){
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);

        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            builder = new Notification.Builder(context, CHANNEL_ID);
        } else {
            builder = new Notification.Builder(context);
        }
        builder.setAutoCancel(true);
        builder.setSmallIcon(icon);
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setContentIntent(pendingIntent);
        getNotificationManager().notify(25, builder.build());
    }
}
