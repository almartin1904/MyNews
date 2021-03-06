package com.openclassroom.alice.mynews.Controller.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassroom.alice.mynews.Controller.Activities.ArticleDisplayActivity;
import com.openclassroom.alice.mynews.Controller.Activities.SearchActivity;
import com.openclassroom.alice.mynews.Model.AlreadyReadArticles;
import com.openclassroom.alice.mynews.Model.ResultOfRequest.NYTArticle;
import com.openclassroom.alice.mynews.Model.ResultOfRequest.RequestResult;
import com.openclassroom.alice.mynews.Model.SearchCriteria;
import com.openclassroom.alice.mynews.R;
import com.openclassroom.alice.mynews.Utils.ItemClickSupport;
import com.openclassroom.alice.mynews.Utils.NYTArticleStreams;
import com.openclassroom.alice.mynews.Views.NYTArticleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class DisplayListOfArticleFragment extends Fragment {

    private static final String KEY_POSITION="position";

    @BindView(R.id.fragment_page_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.fragment_page_swipe_container) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.no_result_txt) TextView mNoResultTxt;

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
        this.configureSwipeRefreshLayout();
        this.configureOnClickRecyclerView();
        return result;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        executeHttpRequestWithRetrofit();
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    private void executeHttpRequestWithRetrofit(){
        int position;
        if (getArguments() != null) {
            //display tabs
            position = getArguments().getInt(KEY_POSITION, -1);
        } else {
            //display result of research
            position=-1;
        }
        switch (position){
            case -1:
                if (getActivity()!=null){
                    Intent i=getActivity().getIntent();
                    SearchCriteria searchCriteria = (SearchCriteria) i.getSerializableExtra(SearchActivity.SEARCH_CRITERIA_EXTRA);
                    this.mDisposable = NYTArticleStreams.streamFetchSearchArticle(searchCriteria.getSearchTerm(), searchCriteria.getSerializedCategories(), searchCriteria.getBeginDateWithAdaptedFormat(), searchCriteria.getEndDateWithAdaptedFormat()).subscribeWith(new DisposableObserver<RequestResult>() {
                        @Override
                        public void onNext(RequestResult requestResult) {
                            List<NYTArticle> articles=requestResult.getNYTArticles();
                            if (articles.isEmpty()){
                                mNoResultTxt.setVisibility(View.VISIBLE);
                                mRecyclerView.setVisibility(View.GONE);
                            } else {
                                mNoResultTxt.setVisibility(View.GONE);
                                mRecyclerView.setVisibility(View.VISIBLE);
                            }
                            updateUI(articles);
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() { }
                    });
                }
                break;
            case 0:
                this.mDisposable = NYTArticleStreams.streamFetchArticleTopStories().subscribeWith(new DisposableObserver<RequestResult>() {
                    @Override
                    public void onNext(RequestResult topStories) {
                        List<NYTArticle> articles=topStories.getNYTArticles();
                        updateUI(articles);
                    }

                    @Override
                    public void onError(Throwable e) { }

                    @Override
                    public void onComplete() { }
                });
                break;
            case 1:
                this.mDisposable = NYTArticleStreams.streamFetchArticleMostPopular().subscribeWith(new DisposableObserver<RequestResult>() {
                    @Override
                    public void onNext(RequestResult topStories) {
                        List<NYTArticle> articles=topStories.getNYTArticles();
                        updateUI(articles);
                    }

                    @Override
                    public void onError(Throwable e) { }

                    @Override
                    public void onComplete() { }
                });
                break;
            case 2:
                this.mDisposable = NYTArticleStreams.streamFetchArticleTopStoriesSport().subscribeWith(new DisposableObserver<RequestResult>() {
                    @Override
                    public void onNext(RequestResult topStories) {
                        List<NYTArticle> articles=topStories.getNYTArticles();
                        updateUI(articles);
                    }

                    @Override
                    public void onError(Throwable e) { }

                    @Override
                    public void onComplete() { }
                });
                break;
            default:
                break;
        }

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

    private void configureSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestWithRetrofit();
            }
        });
    }

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_article_list_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        NYTArticle mNYTArticle = mAdapter.getNYTArticle(position);
                        Intent displayArticleActivity = new Intent(getActivity(), ArticleDisplayActivity.class);
                        displayArticleActivity.putExtra(String.valueOf(R.string.URL), mNYTArticle.getUrl());
                        AlreadyReadArticles alreadyReadArticles=AlreadyReadArticles.getInstance(getContext());
                        alreadyReadArticles.addNewTitleToList(mNYTArticle.getTitle());
                        startActivityForResult(displayArticleActivity, 0);
                    }
                });
    }


    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUI(List<NYTArticle> articles){
        mSwipeRefreshLayout.setRefreshing(false);
        mNYTArticles.clear();
        mNYTArticles.addAll(articles);
        mAdapter.notifyDataSetChanged();
    }

}
