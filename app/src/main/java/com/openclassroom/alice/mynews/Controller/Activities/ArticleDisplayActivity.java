package com.openclassroom.alice.mynews.Controller.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.openclassroom.alice.mynews.Controller.Fragments.ArticleDisplayFragment;
import com.openclassroom.alice.mynews.R;

public class ArticleDisplayActivity extends AppCompatActivity {

    private ArticleDisplayFragment mArticleDisplayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_display);
        this.configureAndShowDisplayArticleFragment();
        this.configureToolbar();
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    private void configureAndShowDisplayArticleFragment(){
        mArticleDisplayFragment = (ArticleDisplayFragment) getSupportFragmentManager().findFragmentById(R.id.activity_article_display_frame_layout);
        if (mArticleDisplayFragment == null) {
            mArticleDisplayFragment = new ArticleDisplayFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_article_display_frame_layout, mArticleDisplayFragment)
                    .commit();
        }
    }

    private void configureToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
