package com.openclassroom.alice.mynews.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Alice on 19 December 2018.
 */
public class AlreadyReadArticleTest {
    List<String> articleList;
    AlreadyReadArticles alreadyReadArticles;
    private static final String TAG = AlreadyReadArticleTest.class.getSimpleName();

    @Before
    public void before(){
        articleList = new ArrayList<>();
        articleList.add("Harry potter");
    }

    @Test
    public void testDeTest(){
        AlreadyReadArticles alreadyReadArticles=mock(AlreadyReadArticles.class);
        when(alreadyReadArticles.loadArticles()).thenReturn(articleList);
        articleList.add("Harry potter");
        articleList = alreadyReadArticles.loadArticles();
        assertEquals(1, articleList.size());
    }

    @Test
    public void testHasAlreadyBeenRead(){
    }

    /*public void addNewTitleToList(String newTitle){
        List<String> readTitles = loadArticles();
        readTitles.add(newTitle);
        saveArticles(readTitles);
    }*/
}
