package ae.sample.nytimesarticles.ui.fragments;

import android.app.Fragment;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import ae.sample.nytimesarticles.R;
import ae.sample.nytimesarticles.db.ArticleDatabase;
import ae.sample.nytimesarticles.db.ArticleModel;
import ae.sample.nytimesarticles.model.PopularArticles;
import ae.sample.nytimesarticles.notifications.NotificationEventReceiver;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailArticleFragment extends Fragment {

    public static String ARTICLE_TITLE = "articleTitle";
    public static String ARTICLE_ID = "articleId";
    public static String ARTICLE_URL = "articleUrl";

    private static String ARTICLE = "article";
    private String articleURLPath;
    private static final String DATABASE_NAME = "article_db";
    private ArticleDatabase articleDatabase;
    private boolean isFavourite;

    @BindView(R.id.wv_article_detail)
    WebView webView;

    @BindView(R.id.pb_spin)
    ProgressBar mProgressBar;

    private PopularArticles article;
    private ArticleModel articleModel;

    public static DetailArticleFragment newInstance(PopularArticles popularArticles) {
        DetailArticleFragment detailArticleFragment = new DetailArticleFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARTICLE, popularArticles);
        detailArticleFragment.setArguments(args);
        return detailArticleFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            article = (PopularArticles) getArguments().getSerializable(ARTICLE);
            articleURLPath = article.getUrl();
        }
        setHasOptionsMenu(true);

        articleDatabase = Room.databaseBuilder(getActivity(), ArticleDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_detail, container, false);
        ButterKnife.bind(this, view);
        initializeUI();
        checkFavourites();
        return view;
    }

    private void checkFavourites() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                articleModel = articleDatabase.daoAccess().fetchOneArticleByArticleId(article.getId());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (articleModel != null) {
                            favouriteButton.setIcon(R.drawable.ic_favorite_filled);
                            isFavourite = true;
                        }
                    }
                });
            }
        }).start();
    }

    private void initializeUI() {
        mProgressBar.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new ArticleBrowser());
        webView.loadUrl(articleURLPath);
    }

    public class ArticleBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private MenuItem favouriteButton;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);

        favouriteButton = menu.findItem(R.id.saveLater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveLater:
                if (!isFavourite) {
                    favouriteButton.setIcon(R.drawable.ic_favorite_filled);
                    saveForLater();
                } else {
                    favouriteButton.setIcon(R.drawable.ic_favorite);
                    removeFavourite();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void removeFavourite() {
        if (articleModel != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    articleDatabase.daoAccess().deleteArticle(articleModel);
                    articleModel = null;
                }
            }).start();
        }
    }

    private void saveForLater() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArticleModel articleModel = new ArticleModel();
                articleModel.setArticleTitle(article.getTitle());
                articleModel.setArticleId(article.getId());
                articleModel.setArticleURL(article.getUrl());
                articleDatabase.daoAccess().insertArticle(articleModel);
            }
        }).start();

        Bundle b = new Bundle();
        b.putString(ARTICLE_TITLE, article.getTitle());
        b.putLong(ARTICLE_ID, article.getId());
        b.putString(ARTICLE_URL, article.getUrl());
        NotificationEventReceiver.setupAlarm(getActivity(), b);
    }
}
