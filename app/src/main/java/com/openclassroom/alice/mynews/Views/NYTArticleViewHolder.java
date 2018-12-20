package com.openclassroom.alice.mynews.Views;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.openclassroom.alice.mynews.Model.AlreadyReadArticles;
import com.openclassroom.alice.mynews.Model.ResultOfRequest.NYTArticle;
import com.openclassroom.alice.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alice on 10 December 2018.
 */
public class NYTArticleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_article_item_title) TextView mTitleTxt;
    @BindView(R.id.picture) ImageView mPicture;
    @BindView(R.id.publishedDate) TextView mDateTxt;
    @BindView(R.id.section) TextView mSectionTxt;

    private Context mContext;

    private static final String TAG = NYTArticleViewHolder.class.getSimpleName();

    NYTArticleViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mContext=context;
    }

    void updateWithNYTArticle(NYTArticle mNYTArticle, RequestManager glide){

        this.mTitleTxt.setText(mNYTArticle.getTitle());
        this.mDateTxt.setText(mNYTArticle.getPublishedDate());
        this.mSectionTxt.setText(mNYTArticle.getSectionAndSubsection());

        try {
            glide.load(mNYTArticle.getImageURL()).apply(RequestOptions.centerCropTransform()).into(mPicture);
        } catch (Exception e){
            Log.e(TAG, "updateWithNYTArticle: " + e.getMessage());
        }

        if (alreadyReadArticle(mNYTArticle.getTitle())){
            this.mTitleTxt.setTextColor(mContext.getResources().getColor(R.color.colorAlreadyRead));
        } else {
            this.mTitleTxt.setTextColor(Color.BLACK);
        }
    }

    private boolean alreadyReadArticle(String title) {
        AlreadyReadArticles alreadyReadArticles=AlreadyReadArticles.getInstance(mContext);
        return alreadyReadArticles.hasAlreadyBeenRead(title);
    }
}
