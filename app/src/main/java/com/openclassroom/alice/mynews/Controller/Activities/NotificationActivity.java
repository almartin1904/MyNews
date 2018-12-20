package com.openclassroom.alice.mynews.Controller.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments.CategoriesFragment;
import com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments.KeyWordFragment;
import com.openclassroom.alice.mynews.R;

public class NotificationActivity extends AppCompatActivity {

    KeyWordFragment mKeyWordFragment;
    CategoriesFragment mCategoriesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        this.configureToolbar();
        this.configureAndShowFragments();
    }

    private void configureToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void configureAndShowFragments(){
        mKeyWordFragment = (KeyWordFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_keyword);
        mCategoriesFragment = (CategoriesFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_categories);

        if (mKeyWordFragment == null) {
            mKeyWordFragment = new KeyWordFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_keyword, mKeyWordFragment)
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
