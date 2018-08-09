package ae.sample.nytimesarticles.notifications;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import ae.sample.nytimesarticles.R;
import ae.sample.nytimesarticles.ui.activities.MainActivity;

import static ae.sample.nytimesarticles.ui.fragments.DetailArticleFragment.ARTICLE_TITLE;

/**
 * Created by Farooq Arshed on 7/14/18.
 */
public class NotificationIntentService extends JobIntentService {

    private static final int NOTIFICATION_ID = 112312;
    private static final String ACTION_START = "ACTION_START";
    public static final String CHANNEL_ID = "Notifciation_Channel";

    private NotificationManagerCompat notificationManager = null;

    public NotificationIntentService() {
        super();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(getClass().getSimpleName(), "onHandleIntent, started handling a notification event");
        try {
            String action = intent.getAction();
            if (ACTION_START.equals(action)) {
                Bundle data = intent.getExtras().getBundle("data");
                processStartNotification(data);
            }
        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

    public static Intent createIntentNotificationService(Context context, Bundle bundle) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_START);
        intent.putExtra("data", bundle);
        return intent;
    }

    private void processStartNotification(Bundle data) {
        notificationManager = NotificationManagerCompat.from(getApplicationContext());
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("data", data);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Article Reminder")
                .setContentText("Read the article " + data.getString(ARTICLE_TITLE))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());

        stopSelf();

    }

}