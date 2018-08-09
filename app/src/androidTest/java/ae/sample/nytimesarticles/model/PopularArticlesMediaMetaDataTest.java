package ae.sample.nytimesarticles.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PopularArticlesMediaMetaDataTest {

    private PopularArticlesMediaMetaData popularArticlesMediaMetaData;
    @Before
    public void setUp() throws Exception {
        popularArticlesMediaMetaData = new PopularArticlesMediaMetaData();
    }


    @Test
    public void setUrl() throws Exception {
        assertNotNull(popularArticlesMediaMetaData);
    }

    @After
    public void tearDown() throws Exception {
        popularArticlesMediaMetaData = null;
    }

}