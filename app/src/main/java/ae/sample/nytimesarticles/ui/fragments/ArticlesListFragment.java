package ae.sample.nytimesarticles.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import ae.sample.nytimesarticles.presenter.ArticleClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ArticlesListFragment extends Fragment implements ArticleClickListener{

    private Unbinder unbinder;
    @BindView(R.id.rv_articlelist)
    RecyclerView rv_articleslist;
    @BindView(R.id.pb_spin)
    ProgressBar pb_spin;

    private String URL_POPULAR_ARTICLES = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=";
    private String API_KEY = "cd2506cf35504016a7579eea094ad1bd";
    private PopularArticlesRecyclerViewAdapter mAdapter;
    private List<PopularArticles> popularArticles;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initializeUI();
        androidNetworkCalltoGetArticles();
        return view;
    }

    private void initializeUI() {
        pb_spin.setVisibility(View.VISIBLE);
        popularArticles = new ArrayList<>();
        mAdapter = new PopularArticlesRecyclerViewAdapter(popularArticles,   this);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_articleslist.setLayoutManager(horizontalLayoutManager);
        rv_articleslist.setAdapter(mAdapter);
    }

    private void androidNetworkCalltoGetArticles() {
        AndroidNetworking.get(URL_POPULAR_ARTICLES+API_KEY)
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(PopularArticlesResponse.class, new ParsedRequestListener<PopularArticlesResponse>() {
                    @Override
                    public void onResponse(PopularArticlesResponse articlesResponse) {
                        pb_spin.setVisibility(View.INVISIBLE);
                        popularArticles.clear();
                        popularArticles.addAll(articlesResponse.getpopularArticles());
                        mAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d("TAG", anError.getErrorBody());
                    }
                });
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onArticleCardClickListener(int position) {
        DetailArticleFragment fragment = DetailArticleFragment.newInstance(popularArticles.get(position));
        android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_conataier, fragment);
        fragmentTransaction.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }
}
