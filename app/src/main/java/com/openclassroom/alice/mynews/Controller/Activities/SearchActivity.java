package com.openclassroom.alice.mynews.Controller.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments.CategoriesFragment;
import com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments.DatesFragment;
import com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments.KeyWordFragment;
import com.openclassroom.alice.mynews.Model.SearchCriteria;
import com.openclassroom.alice.mynews.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    private CategoriesFragment mCategoriesFragment;
    private DatesFragment mDatesFragment;
    private KeyWordFragment mKeyWordFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        this.configureAndShowFragments();
        this.configureToolbar();
    }

    //-------------------
    // CONFIGURATION
    //-------------------

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

    //------------------------
    // LAUNCH SEARCH
    //------------------------

    @OnClick(R.id.search_btn)
    public void submit(View view) {
        SearchCriteria searchCriteria = createSearchCriteria();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.ErrorTitle);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //Test if dates format is ok
        if (!searchCriteria.dateFormatIsOk()){
            builder.setMessage(R.string.wrongDateFormatMessage);
            builder.show();
        } else {
            if (searchCriteria.compareDates()){
                builder.setMessage(R.string.comparedDates);
                builder.show();
            } else {
                //Search criterias are not Ok
                if (mCategoriesFragment.searchNOk() || mKeyWordFragment.searchNOk())
                {
                    builder.setMessage(R.string.missFilterMessage);
                    builder.show();
                }
                else {
                    //search criterias are Ok
                    Intent resultActivity = new Intent(SearchActivity.this, SearchResultActivity.class);
                    resultActivity.putExtra(String.valueOf(R.string.SearchCriteriaExtra), searchCriteria);
                    startActivity(resultActivity);
                }
            }
        }
    }

    private SearchCriteria createSearchCriteria() {
        String queryTerm = mKeyWordFragment.getKeyKeyWord();
        String beginDate = mDatesFragment.getBeginDate();
        String endDate = mDatesFragment.getEndDate();
        List<String> categories=mCategoriesFragment.getListOfCategories();
        return new SearchCriteria(queryTerm,beginDate, endDate, categories);
    }
}
