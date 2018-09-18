
package ae.sample.nytimesarticles.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Response Model for Popular Articles
 */
public class PopularArticlesMedia  implements Serializable {

    @SerializedName("type")
    private String type;

    @SerializedName("subtype")
    private String subtype;

    @SerializedName("caption")
    private String caption;

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("approved_for_syndication")
    private int approvedForSyndication;

    @SerializedName("media-metadata")
    private List<PopularArticlesMediaMetaData> mediaMetadata = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getApprovedForSyndication() {
        return approvedForSyndication;
    }

    public void setApprovedForSyndication(int approvedForSyndication) {
        this.approvedForSyndication = approvedForSyndication;
    }

    public List<PopularArticlesMediaMetaData> getMediaMetadata() {
        return mediaMetadata;
    }

    public void setMediaMetadata(List<PopularArticlesMediaMetaData> mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
    }

}
