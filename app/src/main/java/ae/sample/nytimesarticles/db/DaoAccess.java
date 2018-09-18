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
 * <p>
 * Class for Accessing the database
 */
@Dao
public interface DaoAccess {

    @Insert
    void insertArticle(ArticleModel movies);

    /**
     * Query database to get one article
     *
     * @param articleId Article Id
     * @return Fetched Article Model otherwise null
     */
    @Query("SELECT*FROM ArticleModel WHERE articleId =:articleId")
    ArticleModel fetchOneArticleByArticleId(long articleId);

    /**
     * Query database to get all saved articles
     *
     * @return List of Articles saved
     */
    @Query("SELECT*FROM ArticleModel")
    List<ArticleModel> fetchAllArticle();

    /**
     * Select all articles.
     *
     * @return A {@link Cursor} of all the cheeses in the table.
     */
    @Query("SELECT * FROM ArticleModel")
    Cursor selectAll();

    /**
     * Update database to save articles
     *
     * @param articleModel Articles Model to update
     */
    @Update
    void updateArticle(ArticleModel articleModel);

    /**
     * Delete the article from database
     *
     * @param articleModel Article Model to delete
     */
    @Delete
    void deleteArticle(ArticleModel articleModel);
}