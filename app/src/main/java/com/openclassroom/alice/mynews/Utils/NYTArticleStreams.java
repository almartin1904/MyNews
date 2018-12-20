package com.openclassroom.alice.mynews.Utils;

import com.openclassroom.alice.mynews.Model.ResultOfRequest.RequestResult;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alice on 10 December 2018.
 */
public class NYTArticleStreams {
    public static Observable<RequestResult> streamFetchArticleTopStories() {
        NYTArticleService nytArticleService = NYTArticleService.retrofit.create(NYTArticleService.class);
        return nytArticleService.getTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<RequestResult> streamFetchArticleMostPopular() {
        NYTArticleService nytArticleService = NYTArticleService.retrofit.create(NYTArticleService.class);
        return nytArticleService.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<RequestResult> streamFetchArticleTopStoriesSport() {
        NYTArticleService nytArticleService = NYTArticleService.retrofit.create(NYTArticleService.class);
        return nytArticleService.getSport()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
