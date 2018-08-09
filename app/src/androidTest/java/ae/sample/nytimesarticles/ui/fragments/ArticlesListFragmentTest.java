package ae.sample.nytimesarticles.ui.fragments;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import ae.sample.nytimesarticles.ui.activities.MainActivity;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;


public class ArticlesListFragmentTest {

    private MockWebServer server;

    @SuppressWarnings("CanBeFinal")
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
    }

    @Test
    public void  androidNetworkCalltoGetArticles() throws Exception {

        Intent intent = new Intent();
        mainActivityActivityTestRule.launchActivity(intent);
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBodyDelay((long) 10.0, TimeUnit.SECONDS));
    }


    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

}