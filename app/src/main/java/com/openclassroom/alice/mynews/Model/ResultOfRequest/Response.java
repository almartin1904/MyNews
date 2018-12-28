package com.openclassroom.alice.mynews.Model.ResultOfRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alice on 13 December 2018.
 */
public class Response {
    @SerializedName("docs")
    @Expose
    private List<NYTArticle> docs = null;

    public Response(String API, String title){
        this.docs=new ArrayList<>();
        docs.add(new NYTArticle(API, title,"","","","",""));
    }

    public List<NYTArticle> getDocs() {
        return docs;
    }
}
