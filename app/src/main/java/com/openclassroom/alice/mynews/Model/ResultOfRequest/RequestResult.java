package com.openclassroom.alice.mynews.Model.ResultOfRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.openclassroom.alice.mynews.Model.ResultOfRequest.NYTArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alice on 10 December 2018.
 */
public class RequestResult {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<NYTArticle> mNYTArticles = null;
    @SerializedName("response")
    @Expose
    private Response response;

    public RequestResult(String API, String title){
        switch (API){
            case NYTArticle.API_TOP_STORIES:
                this.mNYTArticles = new ArrayList<>();
                mNYTArticles.add(new NYTArticle(API, title,"","","","",""));
                break;
            case NYTArticle.API_MOST_POPULAR:
                this.mNYTArticles = new ArrayList<>();
                mNYTArticles.add(new NYTArticle(API, title,"","","","",""));
                break;
            case NYTArticle.API_ARTICLE_SEARCH:
                this.response = new Response(API, title);
                break;
            default:
        }
    }

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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<NYTArticle> getNYTArticles() {
        if (mNYTArticles!=null){
            return mNYTArticles;
        }
        else {
            return response.getDocs();
        }
    }

    public void setNYTArticles(List<NYTArticle> mNYTArticles) {
        this.mNYTArticles = mNYTArticles;
    }
}
