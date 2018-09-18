package ae.sample.nytimesarticles.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ae.sample.nytimesarticles.R;

import static ae.sample.nytimesarticles.ui.fragments.DetailArticleFragment.ARTICLE_TITLE;

/**
 * Starting point of the app.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_title);

        if (getIntent() != null && getIntent().hasExtra("data")) {
            Bundle data = getIntent().getBundleExtra("data");
            Log.d("asd", "ARTICLE_TITLE::" + data.getString(ARTICLE_TITLE));
        }
    }
}
