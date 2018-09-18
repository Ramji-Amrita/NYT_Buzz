package ae.sample.nytimesarticles.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Farooq Arshed on 7/14/18.
 */
public class NotificationEventReceiver extends BroadcastReceiver {

    private static final String ACTION_START_NOTIFICATION_SERVICE = "ACTION_START_NOTIFICATION_SERVICE";

    private static final int NOTIFICATIONS_INTERVAL_IN_MILLISECONDS = 2 * 1000 * 60;
    private int JOB_ID = 0x7b;

    /**
     * Setup the alarm for notification.
     *
     * @param context
     * @param bundle
     */
    public static void setupAlarm(Context context, Bundle bundle) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                NOTIFICATIONS_INTERVAL_IN_MILLISECONDS,
                getPendingIntent(context, bundle));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Intent serviceIntent = null;
        if (ACTION_START_NOTIFICATION_SERVICE.equals(action)) {

            Bundle data = intent.getExtras().getBundle("data");
            Log.i(getClass().getSimpleName(), "onReceive from alarm, starting notification service");
            serviceIntent = NotificationIntentService.createIntentNotificationService(context, data);
        }

        if (serviceIntent != null) {
            NotificationIntentService.enqueueWork(context, NotificationIntentService.class, JOB_ID, serviceIntent);
        }
    }

    /**
     * Get the correct Pending Intent with correct values.
     *
     * @param context Context
     * @param bundle  Bundle to be passed to Pending Intent.
     * @return Pending Intent
     */
    private static PendingIntent getPendingIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, NotificationEventReceiver.class);
        intent.setAction(ACTION_START_NOTIFICATION_SERVICE);
        intent.putExtra("data", bundle);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}