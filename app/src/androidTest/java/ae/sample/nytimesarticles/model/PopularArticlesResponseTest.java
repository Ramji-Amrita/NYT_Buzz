package ae.sample.nytimesarticles.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ae.sample.nytimesarticles.ui.fragments.ArticlesListFragment;

import static org.junit.Assert.assertNotNull;


public class PopularArticlesResponseTest {
    private ArticlesListFragment popularArticlesResponse;

    @Before
    public void setUp() throws Exception {
        popularArticlesResponse = new ArticlesListFragment();
    }

    @Test
    public void setUrl() throws Exception {
        assertNotNull(popularArticlesResponse);
    }

    @After
    public void tearDown() throws Exception {
        popularArticlesResponse = null;
    }

}