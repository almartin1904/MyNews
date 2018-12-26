package com.openclassroom.alice.mynews.Controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

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

    @Override
    public void onReceive(final Context context, Intent intent) {

        SearchCriteria mSearchCriteria = (SearchCriteria) intent.getSerializableExtra(String.valueOf(R.string.SearchCriteriaExtra));
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
        Intent intent1 = new Intent(context, NotificationIntentService.class);
        intent1.putExtra(String.valueOf(R.string.NbArticles), nbResponse);
        context.startService(intent1);
    }
}
