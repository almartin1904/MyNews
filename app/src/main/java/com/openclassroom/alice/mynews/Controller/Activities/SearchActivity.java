package com.openclassroom.alice.mynews.Controller.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments.CategoriesFragment;
import com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments.DatesFragment;
import com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments.KeyWordFragment;
import com.openclassroom.alice.mynews.R;

import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    private CategoriesFragment mCategoriesFragment;
    private DatesFragment mDatesFragment;
    private KeyWordFragment mKeyWordFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        configureAndShowFragments();
        configureToolbar();
    }

    private void configureToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    //todo : is it posible to avoid copy-paste
    private void configureAndShowFragments(){
        mKeyWordFragment = (KeyWordFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_keyword);
        mDatesFragment = (DatesFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_dates);
        mCategoriesFragment = (CategoriesFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_categories);

        if (mKeyWordFragment == null) {
            mKeyWordFragment = new KeyWordFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_keyword, mKeyWordFragment)
                    .commit();
        }
        if (mDatesFragment == null) {
            mDatesFragment = new DatesFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_dates, mDatesFragment)
                    .commit();
        }
        if (mCategoriesFragment == null) {
            mCategoriesFragment = new CategoriesFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_categories, mCategoriesFragment)
                    .commit();
        }
    }
}
