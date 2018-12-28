package com.openclassroom.alice.mynews.Controller.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.openclassroom.alice.mynews.Controller.Fragments.DisplayListOfArticleFragment;
import com.openclassroom.alice.mynews.R;

public class SearchResultActivity extends AppCompatActivity {

    private DisplayListOfArticleFragment mDisplayListOfArticleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        this.configureAndShowFragment();
        this.configureToolbar();
    }

    private void configureToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void configureAndShowFragment() {
        mDisplayListOfArticleFragment = (DisplayListOfArticleFragment) getSupportFragmentManager().findFragmentById(R.id.activity_search_result_frame_layout);

        if (mDisplayListOfArticleFragment == null) {
            mDisplayListOfArticleFragment = new DisplayListOfArticleFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_result_frame_layout, mDisplayListOfArticleFragment)
                    .commit();
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
