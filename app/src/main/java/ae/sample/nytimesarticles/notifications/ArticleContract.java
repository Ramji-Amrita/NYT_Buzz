package ae.sample.nytimesarticles.notifications;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Farooq Arshed on 8/8/18.
 */
public final class ArticleContract {


    /**
     * The authority of the lentitems provider.
     */
    public static final String AUTHORITY =
            "de.openminds.samples.cpsample.lentitems";
    /**
     * The content URI for the top-level
     * lentitems authority.
     */
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY);

    public ArticleContract(Context context) {

    }

    /**
     * Constants for the Items table
     * of the lentitems provider.
     */
    /**
     * Constants for the Items table
     * of the lentitems provider.
     */
    public static final class Items
            implements CommonColumns {
        /**
         * The content URI for this table.
         */
        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(
                        ArticleContract.CONTENT_URI,
                        "items");
        /**
         * The mime type of a directory of items.
         */
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE +
                        "/vnd.de.openminds.lentitems_items";
        /**
         * The mime type of a single item.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE +
                        "/vnd.de.openminds.lentitems_items";
        /**
         * A projection of all columns
         * in the items table.
         */
//        public static final String[] PROJECTION_ALL =
//                {_ID, NAME, BORROWER};
//        /**
//         * The default sort order for
//         * queries containing NAME fields.
//         */
//        public static final String SORT_ORDER_DEFAULT =
//                NAME + " ASC";
    }

    /**
     * This interface defines common columns
     * found in multiple tables.
     */
    public static interface CommonColumns
            extends BaseColumns {
    }
}