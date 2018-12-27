package com.openclassroom.alice.mynews.Controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.openclassroom.alice.mynews.Controller.Activities.SearchActivity;
import com.openclassroom.alice.mynews.Model.ResultOfRequest.NYTArticle;
import com.openclassroom.alice.mynews.Model.ResultOfRequest.RequestResult;
import com.openclassroom.alice.mynews.Model.SearchCriteria;
import com.openclassroom.alice.mynews.R;
import com.openclassroom.alice.mynews.Utils.NYTArticleStreams;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Alice on 13 December 2018.
 */
public class SendNotificationReceiver extends BroadcastReceiver {

    Disposable mDisposable;
    public static final String NB_ARTICLE_RESULT = "NB_ARTICLE_RESULT";

    @Override
    public void onReceive(final Context context, Intent intent) {

        SearchCriteria mSearchCriteria = (SearchCriteria) intent.getSerializableExtra(SearchActivity.SEARCH_CRITERIA_EXTRA);
        this.mDisposable = NYTArticleStreams.streamFetchSearchArticle(mSearchCriteria.getSearchTerm(), mSearchCriteria.getSerializedCategories(), mSearchCriteria.getBeginDateWithAdaptedFormat(), mSearchCriteria.getEndDateWithAdaptedFormat()).subscribeWith(new DisposableObserver<RequestResult>() {
            @Override
            public void onNext(RequestResult requestResult) {
                List<NYTArticle> articles=requestResult.getNYTArticles();
                if (articles.size()!=0){
                    sendNotification(context, articles.size());
                }
            }

            @Override
            public void onError(Throwable e) {  }

            @Override
            public void onComplete() { }
        });
    }

    private void sendNotification(Context context, int nbResponse){
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.putExtra(NB_ARTICLE_RESULT, nbResponse);
        context.startService(intent);
    }
}
