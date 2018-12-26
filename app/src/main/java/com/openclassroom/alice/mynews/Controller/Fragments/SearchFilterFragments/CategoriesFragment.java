package com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.openclassroom.alice.mynews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesFragment extends Fragment {

    public static final String KEY_ARTS = "KEY_ARTS";
    public static final String KEY_BUSINESS = "KEY_BUSINESS";
    public static final String KEY_ENTREPRENEURS = "KEY_ENTREPRENEURS";
    public static final String KEY_POLITICS = "KEY_POLITICS";
    public static final String KEY_SPORTS = "KEY_SPORTS";
    public static final String KEY_TRAVEL = "KEY_TRAVEL";

    @BindView(R.id.checkBox_category_arts) CheckBox mArtsCb;
    @BindView(R.id.checkBox_category_business) CheckBox mBusinessCb;
    @BindView(R.id.checkBox_category_entrepreneurs) CheckBox mEntrepreneursCb;
    @BindView(R.id.checkBox_category_politics) CheckBox mPoliticsCb;
    @BindView(R.id.checkBox_category_sports) CheckBox mSportsCb;
    @BindView(R.id.checkBox_category_travel) CheckBox mTravelCb;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void saveCheckBoxState(SharedPreferences mPreferences){
        mPreferences.edit().putBoolean(KEY_ARTS, mArtsCb.isChecked()).apply();
        mPreferences.edit().putBoolean(KEY_BUSINESS, mBusinessCb.isChecked()).apply();
        mPreferences.edit().putBoolean(KEY_ENTREPRENEURS, mEntrepreneursCb.isChecked()).apply();
        mPreferences.edit().putBoolean(KEY_POLITICS, mPoliticsCb.isChecked()).apply();
        mPreferences.edit().putBoolean(KEY_SPORTS, mSportsCb.isChecked()).apply();
        mPreferences.edit().putBoolean(KEY_TRAVEL, mTravelCb.isChecked()).apply();
    }

    public void displayCheckBoxState(SharedPreferences mPreferences){
        mArtsCb.setChecked(mPreferences.getBoolean(KEY_ARTS, true));
        mBusinessCb.setChecked(mPreferences.getBoolean(KEY_BUSINESS, false));
        mEntrepreneursCb.setChecked(mPreferences.getBoolean(KEY_ENTREPRENEURS, false));
        mPoliticsCb.setChecked(mPreferences.getBoolean(KEY_POLITICS, false));
        mSportsCb.setChecked(mPreferences.getBoolean(KEY_SPORTS, true));
        mTravelCb.setChecked(mPreferences.getBoolean(KEY_TRAVEL, true));
    }

    public List<String> getListOfCategories(){
        List<String> categories=new ArrayList<>();
        if (mArtsCb.isChecked()) {categories.add(mArtsCb.getText().toString());}
        if (mBusinessCb.isChecked()) {categories.add(mBusinessCb.getText().toString());}
        if (mEntrepreneursCb.isChecked()) {categories.add(mEntrepreneursCb.getText().toString());}
        if (mPoliticsCb.isChecked()) {categories.add(mPoliticsCb.getText().toString());}
        if (mSportsCb.isChecked()) {categories.add(mSportsCb.getText().toString());}
        if (mTravelCb.isChecked()) {categories.add(mTravelCb.getText().toString());}
        return categories;
    }

    public void disableChange(Boolean bool){
        mArtsCb.setEnabled(!bool);
        mBusinessCb.setEnabled(!bool);
        mEntrepreneursCb.setEnabled(!bool);
        mPoliticsCb.setEnabled(!bool);
        mSportsCb.setEnabled(!bool);
        mTravelCb.setEnabled(!bool);
    }

}
