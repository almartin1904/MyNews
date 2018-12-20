package com.openclassroom.alice.mynews.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.openclassroom.alice.mynews.Model.ResultOfRequest.NYTArticle;
import com.openclassroom.alice.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alice on 20 December 2018.
 */
public class NYTArticleViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.fragment_article_item_title) TextView titleTxt;
    @BindView(R.id.picture) ImageView picture;
    @BindView(R.id.publishedDate) TextView dateTxt;
    @BindView(R.id.section) TextView sectionTxt;
    private Context mContext;

    private static final String TAG = NYTArticleViewHolder.class.getSimpleName();


    NYTArticleViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mContext=context;
    }

    void updateWithNYTArticle(NYTArticle mNYTArticle, RequestManager glide){

        this.titleTxt.setText(mNYTArticle.getTitle());
        this.dateTxt.setText(mNYTArticle.getPublishedDate());
        this.sectionTxt.setText(mNYTArticle.getSection());

        try {
            glide.load(mNYTArticle.getImageURL()).apply(RequestOptions.centerCropTransform()).into(picture);
        } catch (Exception e){
            Log.e(TAG, "updateWithNYTArticle: " + e.getMessage());
        }
    }
}
