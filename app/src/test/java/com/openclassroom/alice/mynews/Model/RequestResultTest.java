package com.openclassroom.alice.mynews.Model;

import com.openclassroom.alice.mynews.Model.ResultOfRequest.RequestResult;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alice on 19 December 2018.
 */
public class RequestResultTest {

    private static final String TITLE = "MyTitle";
    private static final String API_TOP_STORIES= "TopStories";
    private static final String API_MOST_POPULAR= "MostPopular";
    private static final String API_ARTICLE_SEARCH= "ArticleSearch";

    @Test
    public void testGetNYTArticlesApiTopStories(){
        RequestResult requestResult = new RequestResult(API_TOP_STORIES, TITLE);
        assertEquals(TITLE, requestResult.getNYTArticles().get(0).getTitle());
    }

    @Test
    public void testGetNYTArticlesApiMostPopular(){
        RequestResult requestResult = new RequestResult(API_MOST_POPULAR, TITLE);
        assertEquals(TITLE, requestResult.getNYTArticles().get(0).getTitle());
    }


}
