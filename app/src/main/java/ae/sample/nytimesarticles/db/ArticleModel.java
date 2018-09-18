package ae.sample.nytimesarticles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Farooq Arshed on 8/8/18.
 * <p>
 * Database Model Class
 */
@Entity
public class ArticleModel {
    @NonNull
    @PrimaryKey
    private long articleId;

    @NonNull
    private String articleTitle;

    @NonNull
    private String articleURL;

    public ArticleModel() {
    }

    @NonNull
    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(@NonNull long articleId) {
        this.articleId = articleId;
    }

    @NonNull
    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(@NonNull String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @NonNull
    public String getArticleURL() {
        return articleURL;
    }

    public void setArticleURL(@NonNull String articleURL) {
        this.articleURL = articleURL;
    }
}