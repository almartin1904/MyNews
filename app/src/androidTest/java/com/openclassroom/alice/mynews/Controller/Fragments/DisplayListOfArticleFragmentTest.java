package com.openclassroom.alice.mynews.Controller.Fragments;

import com.openclassroom.alice.mynews.Model.ResultOfRequest.RequestResult;
import com.openclassroom.alice.mynews.Utils.NYTArticleStreams;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

/**
 * Created by Alice on 27 December 2018.
 */
public class DisplayListOfArticleFragmentTest {
    @Test
    public void fetchArticleTopStories() throws Exception {
        Observable<RequestResult> observableUsers = NYTArticleStreams.streamFetchArticleTopStories();
        TestObserver<RequestResult> testObserver = new TestObserver<>();
        observableUsers.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        RequestResult articleFetched = testObserver.values().get(0);
        assertEquals((long) articleFetched.getNumResults(), articleFetched.getNYTArticles().size());
    }

    @Test
    public void fetchArticleMostPopular() throws Exception {
        Observable<RequestResult> observableArticles = NYTArticleStreams.streamFetchArticleMostPopular();
        TestObserver<RequestResult> testObserver = new TestObserver<>();
        observableArticles.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        RequestResult articleFetched = testObserver.values().get(0);
        assertEquals(Math.min(articleFetched.getNumResults(), 20), articleFetched.getNYTArticles().size());
    }

    @Test
    public void fetchArticleTopStoriesSport() throws Exception {
        Observable<RequestResult> observableArticles = NYTArticleStreams.streamFetchArticleTopStoriesSport();
        TestObserver<RequestResult> testObserver = new TestObserver<>();
        observableArticles.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        RequestResult articleFetched = testObserver.values().get(0);
        assertEquals(Math.min(articleFetched.getNumResults(), 20), articleFetched.getNYTArticles().size());
    }

    @Test
    public void fetchArticleSearchArticle() throws Exception {
        Observable<RequestResult> observableArticles = NYTArticleStreams.streamFetchSearchArticle("France", "Politics", "18000101", "20190101");
        TestObserver<RequestResult> testObserver = new TestObserver<>();
        observableArticles.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        RequestResult articleFetched = testObserver.values().get(0);
        assertEquals(10, articleFetched.getNYTArticles().size());
    }

}