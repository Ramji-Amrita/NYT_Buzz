package ae.sample.nytimesarticles.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Farooq Arshed on 8/8/18.
 *
 * Article Database Class for Room Library
 */

@Database(entities = {ArticleModel.class}, version = 1, exportSchema = false)
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}