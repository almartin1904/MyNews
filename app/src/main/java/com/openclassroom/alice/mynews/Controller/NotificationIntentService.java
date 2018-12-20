package com.openclassroom.alice.mynews.Controller;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;

import com.openclassroom.alice.mynews.Controller.Activities.NotificationActivity;
import com.openclassroom.alice.mynews.R;

/**
 * Created by Alice on 20 December 2018.
 */
public class NotificationIntentService extends IntentService {

    private static final int NOTIFICATION_ID = 3;
    private static final String TAG = "NotificationIntent";

    public NotificationIntentService() {
        super("NotificationIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(getResources().getString(R.string.NotifTitle));
        int numberOfNewArticles = intent.getIntExtra(String.valueOf(R.string.NbArticles), 0);
        if (numberOfNewArticles==10)
        {
            builder.setContentText(getResources().getString(R.string.NotifMessageBegin) + " more than " + String.valueOf(numberOfNewArticles) + " " + getResources().getString(R.string.NotifMessageEnd));
        } else {
            builder.setContentText(getResources().getString(R.string.NotifMessageBegin) + " " + String.valueOf(numberOfNewArticles) + " " + getResources().getString(R.string.NotifMessageEnd));
        }
        builder.setSmallIcon(R.drawable.logo);

        Intent notifyIntent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
    }
}
