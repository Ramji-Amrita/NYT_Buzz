package ae.sample.nytimesarticles;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.androidnetworking.AndroidNetworking;

import static ae.sample.nytimesarticles.notifications.NotificationIntentService.CHANNEL_ID;


public class NYTimesArticlesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    /**
     * Creating channel to display notification.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String id = CHANNEL_ID;
        CharSequence name = "Show Article";
        String description = "Remind about saved articles.";
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);
        mChannel.setDescription(description);
        mChannel.enableLights(true);
        mChannel.setLightColor(Color.RED);
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        mNotificationManager.createNotificationChannel(mChannel);
    }
}