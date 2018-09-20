package ae.sample.nytimesarticles.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ae.sample.nytimesarticles.R;

/**
 * Starting point of the app.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_title);
    }
}
