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
        //1 - Get the stream
        Observable<RequestResult> observableUsers = NYTArticleStreams.streamFetchArticleTopStories();
        //2 - Create a new TestObserver
        TestObserver<RequestResult> testObserver = new TestObserver<>();
        //3 - Launch observable
        observableUsers.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // 3.3 - Await the stream terminated before continue

        // 4 - Get list of user fetched
        RequestResult articleFetched = testObserver.values().get(0);

        assertEquals((long) articleFetched.getNumResults(), articleFetched.getNYTArticles().size());
    }

    @Test
    public void fetchArticleMostPopular() throws Exception {
        //1 - Get the stream
        Observable<RequestResult> observableUsers = NYTArticleStreams.streamFetchArticleMostPopular();
        //2 - Create a new TestObserver
        TestObserver<RequestResult> testObserver = new TestObserver<>();
        //3 - Launch observable
        observableUsers.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // 3.3 - Await the stream terminated before continue

        // 4 - Get list of user fetched
        RequestResult articleFetched = testObserver.values().get(0);

        assertEquals(Math.min(articleFetched.getNumResults(), 20), articleFetched.getNYTArticles().size());
    }

    @Test
    public void fetchArticleTopStoriesSport() throws Exception {
        //1 - Get the stream
        Observable<RequestResult> observableUsers = NYTArticleStreams.streamFetchArticleTopStoriesSport();
        //2 - Create a new TestObserver
        TestObserver<RequestResult> testObserver = new TestObserver<>();
        //3 - Launch observable
        observableUsers.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // 3.3 - Await the stream terminated before continue

        // 4 - Get list of user fetched
        RequestResult articleFetched = testObserver.values().get(0);

        assertEquals(Math.min(articleFetched.getNumResults(), 20), articleFetched.getNYTArticles().size());
    }

    @Test
    public void fetchArticleSearchArticle() throws Exception {
        //1 - Get the stream
        Observable<RequestResult> observableUsers = NYTArticleStreams.streamFetchSearchArticle("France", "Politics", "18000101", "20190101");
        //2 - Create a new TestObserver
        TestObserver<RequestResult> testObserver = new TestObserver<>();
        //3 - Launch observable
        observableUsers.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // 3.3 - Await the stream terminated before continue

        // 4 - Get list of user fetched
        RequestResult articleFetched = testObserver.values().get(0);

        assertEquals(10, articleFetched.getNYTArticles().size());
    }

}