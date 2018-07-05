package ae.sample.nytimesarticles;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;


public class NYTimesArticlesApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}