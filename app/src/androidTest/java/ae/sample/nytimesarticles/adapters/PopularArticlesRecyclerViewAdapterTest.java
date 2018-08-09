package ae.sample.nytimesarticles.adapters;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import ae.sample.nytimesarticles.model.PopularArticles;
import ae.sample.nytimesarticles.ui.fragments.ArticlesListFragment;

import static org.junit.Assert.assertEquals;

public class PopularArticlesRecyclerViewAdapterTest {
    private ArticlesListFragment fragment;

    @Before
    public void setUp() throws Exception {
        fragment = new ArticlesListFragment();
    }


    @Test
    public void adapterLaunchingTest() throws Exception {
        PopularArticlesRecyclerViewAdapter defaultAdapter =
                new PopularArticlesRecyclerViewAdapter(new ArrayList<PopularArticles>(), fragment);
        assertEquals(0, defaultAdapter.getItemCount());
    }

    @After
    public void tearDown() throws Exception {
        fragment = null;
    }

}