package com.openclassroom.alice.mynews.Model;

import com.openclassroom.alice.mynews.Model.ResultOfRequest.NYTArticle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alice on 19 December 2018.
 */
public class NYTArticleTest {

    private static final String API_TOP_STORIES = "TopStories";
    private static final String API_MOST_POPULAR = "MostPopular";
    private static final String API_SEARCH_ARTICLE = "ArticleSearch";
    private static final String TITLE = "theTitle";
    private static final String URL = "theUrl";
    private static final String PUBLISHED_DATE = "theDate";
    private static final String SECTION = "theSection";
    private static final String SUBSECTION = "theSubsection";
    private static final String IMAGE_URL = "theImageURL";

    @Test
    public void testGetTitleApiTopStories(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_TOP_STORIES, TITLE, URL, PUBLISHED_DATE, SECTION, SUBSECTION, IMAGE_URL);
        assertEquals(TITLE, nytArticleTopStories.getTitle());
    }

    @Test
    public void testGetTitleApiMostPopular(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_MOST_POPULAR, TITLE, URL, PUBLISHED_DATE, SECTION, SUBSECTION, IMAGE_URL);
        assertEquals(TITLE, nytArticleTopStories.getTitle());
    }

    @Test
    public void testGetTitleApiSearchArticle(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_SEARCH_ARTICLE, TITLE, URL, PUBLISHED_DATE, SECTION, SUBSECTION, IMAGE_URL);
        assertEquals(TITLE, nytArticleTopStories.getTitle());
    }

    @Test
    public void testGetUrlApiTopStories(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_TOP_STORIES, TITLE, URL, PUBLISHED_DATE, SECTION, SUBSECTION, IMAGE_URL);
        assertEquals(URL, nytArticleTopStories.getUrl());
    }

    @Test
    public void testGetUrlApiMostPopular(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_MOST_POPULAR, TITLE, URL, PUBLISHED_DATE, SECTION, SUBSECTION, IMAGE_URL);
        assertEquals(URL, nytArticleTopStories.getUrl());
    }

    @Test
    public void testGetUrlApiSearchArticle(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_SEARCH_ARTICLE, TITLE, URL, PUBLISHED_DATE, SECTION, SUBSECTION, IMAGE_URL);
        assertEquals(URL, nytArticleTopStories.getUrl());
    }

    @Test
    public void testGetPublishedDateApiTopStories(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_TOP_STORIES, TITLE, URL, "2018-12-19T09:09:22-05:00", SECTION, SUBSECTION, IMAGE_URL);
        assertEquals("19/12/2018", nytArticleTopStories.getPublishedDate());
    }

    @Test
    public void testGetPublishedDateApiMostPopular(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_MOST_POPULAR, TITLE, URL, "2018-12-12", SECTION, SUBSECTION, IMAGE_URL);
        assertEquals("12/12/2018", nytArticleTopStories.getPublishedDate());
    }

    @Test
    public void testGetPublishedDateApiSearchArticle(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_SEARCH_ARTICLE, TITLE, URL, "1927-04-06T00:00:00Z", SECTION, SUBSECTION, IMAGE_URL);
        assertEquals("06/04/1927", nytArticleTopStories.getPublishedDate());
    }

    @Test
    public void testGetSectionAndSubsectionApiTopStories(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_TOP_STORIES, TITLE, URL, PUBLISHED_DATE, SECTION, SUBSECTION, IMAGE_URL);
        assertEquals(SECTION + ">" + SUBSECTION, nytArticleTopStories.getSectionAndSubsection());
    }

    @Test
    public void testGetSectionAndSubsectionApiMostPopular(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_MOST_POPULAR, TITLE, URL, PUBLISHED_DATE, SECTION, SUBSECTION, IMAGE_URL);
        assertEquals(SECTION, nytArticleTopStories.getSectionAndSubsection());
    }

    @Test
    public void testGetSectionAndSubsectionApiSearchArticle(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_SEARCH_ARTICLE, TITLE, URL, PUBLISHED_DATE, SECTION, SUBSECTION, IMAGE_URL);
        assertEquals(SECTION, nytArticleTopStories.getSectionAndSubsection());
    }

    @Test
    public void testGetImageUrlApiTopStories(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_TOP_STORIES, TITLE, URL, PUBLISHED_DATE, SECTION, SUBSECTION, IMAGE_URL);
        assertEquals("https://static01.nyt.com/"+IMAGE_URL, nytArticleTopStories.getImageURL());
    }

    @Test
    public void testGetImageUrlApiMostPopular(){
        NYTArticle nytArticleTopStories = new NYTArticle(API_MOST_POPULAR, TITLE, URL, PUBLISHED_DATE, SECTION, SUBSECTION, IMAGE_URL);
        assertEquals(IMAGE_URL, nytArticleTopStories.getImageURL());
    }

    @Test
    public void testGetImageUrlApiSearchArticle() {
        NYTArticle nytArticleTopStories = new NYTArticle(API_SEARCH_ARTICLE, TITLE, URL, PUBLISHED_DATE, SECTION, SUBSECTION, IMAGE_URL);
        assertEquals(IMAGE_URL, nytArticleTopStories.getImageURL());
    }
}
