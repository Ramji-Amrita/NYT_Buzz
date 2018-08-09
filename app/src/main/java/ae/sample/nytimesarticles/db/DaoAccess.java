package ae.sample.nytimesarticles.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

/**
 * Created by Farooq Arshed on 8/8/18.
 */
@Dao
public interface DaoAccess {

    @Insert
    void insertArticle(ArticleModel movies);

    @Query("SELECT*FROM ArticleModel WHERE articleId =:articleId")
    ArticleModel fetchOneArticleByArticleId(long articleId);

    @Query("SELECT*FROM ArticleModel")
    List<ArticleModel> fetchAllArticle();

    /**
     * Select all articles.
     *
     * @return A {@link Cursor} of all the cheeses in the table.
     */
    @Query("SELECT * FROM ArticleModel")
    Cursor selectAll();

    @Update
    void updateArticle(ArticleModel movies);

    @Delete
    void deleteArticle(ArticleModel movies);
}