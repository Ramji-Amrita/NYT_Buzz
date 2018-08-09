package ae.sample.nytimesarticles.ui.activities;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ae.sample.nytimesarticles.R;
import ae.sample.nytimesarticles.ui.fragments.ArticlesListFragment;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    @SuppressWarnings("CanBeFinal")
    @Rule
    public  ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity  = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void FragmentLaunchTest() throws Exception {

        View view = mainActivity.findViewById(R.id.articlesFragment);
        assertNotNull(view);
        ArticlesListFragment articlesListFragment = new ArticlesListFragment();
        mainActivity.getFragmentManager().beginTransaction().add(view.getId(), articlesListFragment).commitAllowingStateLoss();

        getInstrumentation().waitForIdleSync();

        View view1 = articlesListFragment.getView().findViewById(R.id.rv_articlelist);
        assertNotNull(view1);

    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }


}