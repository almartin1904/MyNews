package com.openclassroom.alice.mynews.Controller.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassroom.alice.mynews.Model.DisplayListOfArticlesAdapter;
import com.openclassroom.alice.mynews.Model.NYTArticle;
import com.openclassroom.alice.mynews.Model.RequestResult;
import com.openclassroom.alice.mynews.R;
import com.openclassroom.alice.mynews.Utils.NYTArticleStreams;
import com.openclassroom.alice.mynews.Views.NYTArticleAdapter;

import java.util.ArrayList;
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

    @BindView(R.id.fragment_page_recycler_view) RecyclerView mRecyclerView;

    private Disposable mDisposable;
    private List<NYTArticle> mNYTArticles;
    private NYTArticleAdapter mAdapter;

    public DisplayListOfArticleFragment() { }

    public static DisplayListOfArticleFragment newInstance(int position) {
        DisplayListOfArticleFragment frag = new DisplayListOfArticleFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);
        return(frag);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_display_list_of_article, container, false);
        ButterKnife.bind(this,result);
        this.configureRecyclerView();
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
        // 1.2 - Execute the stream subscribing to Observable defined inside GithubStream
        this.mDisposable = NYTArticleStreams.streamFetchArticleTopStories().subscribeWith(new DisposableObserver<RequestResult>() {
            @Override
            public void onNext(RequestResult topStories) {
                Log.e(TAG,"On Next");
                // 1.3 - Update UI with list of users
                List<NYTArticle> articles=topStories.getResults();
                updateUI(articles);
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

    // -----------------
    // CONFIGURATION
    // -----------------

    private void configureRecyclerView(){
        this.mNYTArticles = new ArrayList<>();
        this.mAdapter = new NYTArticleAdapter(this.mNYTArticles, Glide.with(this), getContext());
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUI(List<NYTArticle> articles){
        mNYTArticles.clear();
        mNYTArticles.addAll(articles);
        mAdapter.notifyDataSetChanged();
    }

}
