package ae.sample.nytimesarticles.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ae.sample.nytimesarticles.R;
import ae.sample.nytimesarticles.model.PopularArticles;
import ae.sample.nytimesarticles.ui.fragments.ArticleClickListener;
import ae.sample.nytimesarticles.ui.fragments.ArticlesListFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Recycle View Adapter for Handling Views
 */
public class PopularArticlesRecyclerViewAdapter extends RecyclerView.Adapter<PopularArticlesRecyclerViewAdapter.MyViewHolder> {

    private List<PopularArticles> popularArticles;
    private ArticleClickListener articleClickListener;

    public PopularArticlesRecyclerViewAdapter(List<PopularArticles> articlesList, ArticlesListFragment clickListener) {
        popularArticles = articlesList;
        articleClickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_articles_details_item_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvArticleTitle.setText(popularArticles.get(position).getTitle());
        holder.tvArticleDetail.setText(popularArticles.get(position).getByline());
        holder.tvArticleSource.setText(popularArticles.get(position).getSource());
        holder.tvArticleDate.setText(popularArticles.get(position).getPublishedDate());
        holder.imgArticleIcon.setImageUrl(popularArticles.get(position).getMedia().get(0).getMediaMetadata().get(0).getUrl());

        holder.cvAgentItemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                articleClickListener.onArticleCardClickListener(popularArticles.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularArticles.size();
    }

    /**
     * Set Articles from outside and refresh the list
     *
     * @param articlesList Articles List
     */
    public void setArticlesList(List<PopularArticles> articlesList) {
        popularArticles = articlesList;
        notifyDataSetChanged();
    }


    /**
     * Custom View Holder
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_article_icon)
        com.androidnetworking.widget.ANImageView imgArticleIcon;
        @BindView(R.id.tv_article_title)
        TextView tvArticleTitle;
        @BindView(R.id.tv_article_detail)
        TextView tvArticleDetail;
        @BindView(R.id.tv_article_source)
        TextView tvArticleSource;
        @BindView(R.id.tv_article_date)
        TextView tvArticleDate;
        @BindView(R.id.ll_details)
        RelativeLayout llDetails;
        @BindView(R.id.iv_details)
        ImageView ivDetails;
        @BindView(R.id.cvAgentItemContainer)
        CardView cvAgentItemContainer;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
