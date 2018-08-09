package ae.sample.nytimesarticles.content_provider;

import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ProviderTestCase2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ae.sample.nytimesarticles.notifications.ArticleContract;
import ae.sample.nytimesarticles.notifications.NYContentProvider;

/**
 * Created by Farooq Arshed on 8/8/18.
 */
@RunWith(AndroidJUnit4.class)
public class ExampleProviderTestCase extends ProviderTestCase2<NYContentProvider> {

    public ExampleProviderTestCase() {
        super(NYContentProvider.class, ArticleContract.AUTHORITY);
    }

    @Before
    @Override
    public void setUp() throws Exception {
        setContext(InstrumentationRegistry.getTargetContext());
        super.setUp();
    }

    @Test
    public void testQuery() {
        Cursor c = getMockContentResolver().query(ArticleContract.CONTENT_URI, null, null, null, null);
        assertFalse(c.moveToFirst());
        c.close();
    }
}