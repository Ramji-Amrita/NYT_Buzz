package ae.sample.nytimesarticles;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ae.sample.nytimesarticles.ui.activities.MainActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NYTimesArticlesApplicationTest {
    @SuppressWarnings("CanBeFinal")
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity ;

    @Before
    public void setUp() throws Exception {
        mainActivity = null;
    }

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("ae.sample.nytimesarticles", appContext.getPackageName());
    }

    @Test
    public void ApplicationTest() throws Exception {
        mainActivity  = mainActivityActivityTestRule.getActivity();
        assertNotNull(mainActivity);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }


}