package com.openclassroom.alice.mynews.Model;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Alice on 26 December 2018.
 */
public class AlreadyReadArticleTest {

    private static final String TITLE_TEST = "Test";

    @Test
    public void hasAlreadyBeenReadTest(){

        Context appContext = InstrumentationRegistry.getTargetContext();
        AlreadyReadArticles alreadyReadArticle=AlreadyReadArticles.getInstance(appContext);
        alreadyReadArticle.addNewTitleToList(TITLE_TEST);
        assertTrue(alreadyReadArticle.hasAlreadyBeenRead(TITLE_TEST));
    }
}
