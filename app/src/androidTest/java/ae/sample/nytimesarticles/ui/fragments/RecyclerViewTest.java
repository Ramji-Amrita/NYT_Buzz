package ae.sample.nytimesarticles.ui.fragments;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import ae.sample.nytimesarticles.R;
import ae.sample.nytimesarticles.ui.activities.MainActivity;


public class RecyclerViewTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void recyclerViewScrollTest() throws Exception {

        Espresso.onView(ViewMatchers.withId(R.id.rv_articlelist))
                .perform(RecyclerViewActions.scrollToPosition(10));
    }



}