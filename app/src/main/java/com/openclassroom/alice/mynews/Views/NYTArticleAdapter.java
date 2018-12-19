package com.openclassroom.alice.mynews.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.openclassroom.alice.mynews.Model.NYTArticle;
import com.openclassroom.alice.mynews.R;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by Alice on 20 December 2018.
 */
public class NYTArticleAdapter extends RecyclerView.Adapter<NYTArticleViewHolder> {

    private List<NYTArticle> mNYTArticles;
    private RequestManager glide;
    private Context mContext;

    public NYTArticleAdapter(List<NYTArticle> mNYTArticles, RequestManager glide, Context context) {
        this.mNYTArticles = mNYTArticles;
        this.glide = glide;
        this.mContext=context;
    }

    @NonNull
    @Override
    public NYTArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_article_list_item, parent, false);

        return new NYTArticleViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull NYTArticleViewHolder viewHolder, int position) {
        viewHolder.updateWithNYTArticle(this.mNYTArticles.get(position), this.glide);
    }

    public NYTArticle getNYTArticle(int position) {
        return this.mNYTArticles.get(position);
    }

    @Override
    public int getItemCount() {
        return this.mNYTArticles.size();
    }


}
