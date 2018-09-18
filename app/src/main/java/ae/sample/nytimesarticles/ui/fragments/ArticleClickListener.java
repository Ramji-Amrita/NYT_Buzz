package ae.sample.nytimesarticles.ui.fragments;

import ae.sample.nytimesarticles.model.PopularArticles;

/**
 * Callback listener for RecyclerView
 */
public interface ArticleClickListener {
    void onArticleCardClickListener(PopularArticles article);
}
