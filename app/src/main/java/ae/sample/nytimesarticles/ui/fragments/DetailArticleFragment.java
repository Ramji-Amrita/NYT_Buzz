package ae.sample.nytimesarticles.ui.fragments;

import android.app.Fragment;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import ae.sample.nytimesarticles.R;
import ae.sample.nytimesarticles.db.ArticleDatabase;
import ae.sample.nytimesarticles.db.ArticleModel;
import ae.sample.nytimesarticles.model.PopularArticles;
import ae.sample.nytimesarticles.notifications.NotificationEventReceiver;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment to load the details of the article
 */
public class DetailArticleFragment extends Fragment {

    public static String ARTICLE_TITLE = "articleTitle";
    public static String ARTICLE_ID = "articleId";
    public static String ARTICLE_URL = "articleUrl";

    private static String ARTICLE = "article";
    private static final String DATABASE_NAME = "article_db";
    private ArticleDatabase articleDatabase;
    private boolean isFavourite;

    @BindView(R.id.imageView)
    ANImageView imageView;

    @BindView(R.id.titleTxtView)
    TextView titleTxtView;

    @BindView(R.id.authorTxtView)
    TextView authorTxtView;

    @BindView(R.id.dateTxtView)
    TextView dateTxtView;

    @BindView(R.id.abstractTxtView)
    TextView abstractTxtView;

    private PopularArticles article;
    private ArticleModel articleModel;

    /**
     * Create a new instance with correct bundle properties.
     *
     * @param popularArticles Articles To Display
     * @return DetailArticleFragment
     */
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

    /**
     * Check whether the article is already in favourites.
     */
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
                        favouriteButton.setVisible(true);
                    }
                });
            }
        }).start();
    }

    /**
     * Initialize the UI on the page.
     */
    private void initializeUI() {
        imageView.setImageUrl(article.getMedia().get(0).getMediaMetadata().get(0).getUrl());
        titleTxtView.setText(article.getTitle());
        authorTxtView.setText(article.getByline());
        dateTxtView.setText(article.getPublishedDate());
        abstractTxtView.setText(article.getAbstract());
    }

    private MenuItem favouriteButton;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);

        favouriteButton = menu.findItem(R.id.saveLater);
        favouriteButton.setVisible(false);
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
                    isFavourite = false;
                    removeFavourite();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Remove from favourites
     */
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

    /**
     * Save in favourites for later use.
     */
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

    @OnClick(R.id.readFullButton)
    public void readFullArticle() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
        startActivity(browserIntent);
    }
}
