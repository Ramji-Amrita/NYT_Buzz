package ae.sample.nytimesarticles.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PopularArticlesMediaTest {
    private PopularArticlesMedia popularArticlesMedia;
    @Before
    public void setUp() throws Exception {
        popularArticlesMedia = new PopularArticlesMedia();
    }

    @Test
    public void setMediaMetadata() throws Exception {
        assertNotNull(popularArticlesMedia);
    }

    @After
    public void tearDown() throws Exception {
        popularArticlesMedia = null;
    }


}