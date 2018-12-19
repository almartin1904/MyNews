package com.openclassroom.alice.mynews.Controller.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassroom.alice.mynews.Model.DisplayListOfArticlesAdapter;
import com.openclassroom.alice.mynews.Model.NYTArticle;
import com.openclassroom.alice.mynews.Model.RequestResult;
import com.openclassroom.alice.mynews.R;
import com.openclassroom.alice.mynews.Utils.NYTArticleStreams;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayListOfArticleFragment extends Fragment {

    private static final String KEY_POSITION="position";
    private static final String TAG = "ArticleFragment";

    @BindView(R.id.fragment_displayListOfArticle_textview) TextView mTextView;

    private Disposable mDisposable;

    public DisplayListOfArticleFragment() { }

    public static DisplayListOfArticleFragment newInstance(int position) {
        DisplayListOfArticleFragment frag = new DisplayListOfArticleFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);
        return(frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_display_list_of_article, container, false);

        ButterKnife.bind(this,result);
        this.executeHttpRequestWithRetrofit();

        return result;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    // 1 - Execute our Stream
    private void executeHttpRequestWithRetrofit(){
        // 1.1 - Update UI
        this.updateUIWhenStartingHTTPRequest();
        // 1.2 - Execute the stream subscribing to Observable defined inside GithubStream
        this.mDisposable = NYTArticleStreams.streamFetchArticleTopStories().subscribeWith(new DisposableObserver<RequestResult>() {
            @Override
            public void onNext(RequestResult users) {
                Log.e(TAG,"On Next");
                // 1.3 - Update UI with list of users
                updateUIWithListOfUsers(users);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,"On Error"+Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
                Log.e(TAG,"On Complete !!");
            }
        });
    }

    private void disposeWhenDestroy(){
        if (this.mDisposable != null && !this.mDisposable.isDisposed()) this.mDisposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUIWhenStartingHTTPRequest(){
        this.mTextView.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response){
        this.mTextView.setText(response);
    }

    private void updateUIWithListOfUsers(RequestResult result){
        StringBuilder stringBuilder = new StringBuilder();
        List<NYTArticle> nytArticles = result.getResults();
        for (NYTArticle article : nytArticles){
            stringBuilder.append("-"+article.getTitle()+"\n");
            Log.d(TAG, "updateUIWithListOfUsers: " + article.getTitle());
        }
        updateUIWhenStopingHTTPRequest(stringBuilder.toString());
    }

}
