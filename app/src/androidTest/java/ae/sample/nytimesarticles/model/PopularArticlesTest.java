package ae.sample.nytimesarticles.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class PopularArticlesTest {

    private PopularArticles popularArticles;

    @Before
    public void setUp() throws Exception {
        popularArticles = new PopularArticles();
    }

    @Test
    public void setMedia() throws Exception {
        assertNotNull(popularArticles);
    }

    @After
    public void tearDown() throws Exception {
        popularArticles = null;
    }

}