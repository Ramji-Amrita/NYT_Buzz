
package ae.sample.nytimesarticles.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Response Model for Popular Articles
 */
public class PopularArticlesResponse implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("num_results")
    private Integer numResults;


    @SerializedName("results")
    private List<PopularArticles> popularArticles = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<PopularArticles> getpopularArticles() {
        return popularArticles;
    }

    public void setpopularArticles(List<PopularArticles> results) {
        this.popularArticles = results;
    }


}
