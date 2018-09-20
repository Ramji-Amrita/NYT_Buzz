package ae.sample.nytimesarticles.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;
import java.util.List;

import ae.sample.nytimesarticles.R;
import ae.sample.nytimesarticles.adapters.PopularArticlesRecyclerViewAdapter;
import ae.sample.nytimesarticles.model.PopularArticles;
import ae.sample.nytimesarticles.model.PopularArticlesResponse;
import butterknife.BindView;
import butterknife.ButterKnife;

import static ae.sample.nytimesarticles.ui.fragments.DetailArticleFragment.ARTICLE_ID;

/**
 * Fragment to load and display the list of the articles.
 */
public class ArticlesListFragment extends Fragment implements ArticleClickListener {

    private static final String URL_POPULAR_ARTICLES = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/1.json?api-key=cd2506cf35504016a7579eea094ad1bd";

    private long articleId = -1;

    @BindView(R.id.rv_articlelist)
    RecyclerView articlesList;

    @BindView(R.id.pb_spin)
    ProgressBar mProgressBar;

    private PopularArticlesRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity().getIntent() != null && getActivity().getIntent().hasExtra("data")) {
            Bundle data = getActivity().getIntent().getBundleExtra("data");
            if (data != null) {
                articleId = data.getLong(ARTICLE_ID);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_list, container, false);
        ButterKnife.bind(this, view);
        initializeUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadArticles();
    }

    /**
     * Initialize UI elements
     */
    private void initializeUI() {
        mProgressBar.setVisibility(View.VISIBLE);
        mAdapter = new PopularArticlesRecyclerViewAdapter(new ArrayList<PopularArticles>(), this);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        articlesList.setLayoutManager(horizontalLayoutManager);
        articlesList.setAdapter(mAdapter);
    }

    /**
     * Load the articles
     */
    private void loadArticles() {
        AndroidNetworking.get(URL_POPULAR_ARTICLES)
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(PopularArticlesResponse.class, new ParsedRequestListener<PopularArticlesResponse>() {
                    @Override
                    public void onResponse(PopularArticlesResponse articlesResponse) {
                        if (articleId != -1) {
                            PopularArticles article = searchForArticle(articlesResponse.getpopularArticles());
                            if (article == null) {
                                Snackbar.make(getView(), "Could not find the article.", Snackbar.LENGTH_SHORT).show();
                            } else {
                                onArticleCardClickListener(article);
                            }
                            articleId = -1;
                        }
                        loadData(articlesResponse);
                    }

                    private void loadData(PopularArticlesResponse articlesResponse) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        mAdapter.setArticlesList(articlesResponse.getpopularArticles());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("TAG", anError.getErrorBody());
                    }
                });
    }

    /**
     * Recieve which item is clicked.
     *
     * @param article
     */
    @Override
    public void onArticleCardClickListener(PopularArticles article) {
        DetailArticleFragment fragment = DetailArticleFragment.newInstance(article);
        android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_conataier, fragment);
        fragmentTransaction.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    private PopularArticles searchForArticle(List<PopularArticles> articlesList) {
        PopularArticles result = null;
        for (PopularArticles article :
                articlesList) {
            if (article.getId() == articleId) {
                result = article;
                break;
            }
        }
        return result;
    }

}
