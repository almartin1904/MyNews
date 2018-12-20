package com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.openclassroom.alice.mynews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {


    public static final String TAG="CategoriesFragment";

    public static final String KEY_ARTS = "KEY_ARTS";
    public static final String KEY_BUSINESS = "KEY_BUSINESS";
    public static final String KEY_ENTREPRENEURS = "KEY_ENTREPRENEURS";
    public static final String KEY_POLITICS = "KEY_POLITICS";
    public static final String KEY_SPORTS = "KEY_SPORTS";
    public static final String KEY_TRAVEL = "KEY_TRAVEL";

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.checkBox_category_arts) CheckBox artsCb;
    @BindView(R.id.checkBox_category_business) CheckBox businessCb;
    @BindView(R.id.checkBox_category_entrepreneurs) CheckBox entrepreneursCb;
    @BindView(R.id.checkBox_category_politics) CheckBox politicsCb;
    @BindView(R.id.checkBox_category_sports) CheckBox sportsCb;
    @BindView(R.id.checkBox_category_travel) CheckBox travelCb;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_categories, container, false);
        Log.d(TAG, "onCreateView: ");
        ButterKnife.bind(this, view);
        return view;
    }

    public void saveCheckBoxState(SharedPreferences mPreferences){
        mPreferences.edit().putBoolean(KEY_ARTS, artsCb.isChecked()).apply();
        mPreferences.edit().putBoolean(KEY_BUSINESS, businessCb.isChecked()).apply();
        mPreferences.edit().putBoolean(KEY_ENTREPRENEURS, entrepreneursCb.isChecked()).apply();
        mPreferences.edit().putBoolean(KEY_POLITICS, politicsCb.isChecked()).apply();
        mPreferences.edit().putBoolean(KEY_SPORTS, sportsCb.isChecked()).apply();
        mPreferences.edit().putBoolean(KEY_TRAVEL, travelCb.isChecked()).apply();
    }

    public void displayCheckBoxState(SharedPreferences mPreferences){
        artsCb.setChecked(mPreferences.getBoolean(KEY_ARTS, true));
        businessCb.setChecked(mPreferences.getBoolean(KEY_BUSINESS, false));
        entrepreneursCb.setChecked(mPreferences.getBoolean(KEY_ENTREPRENEURS, false));
        politicsCb.setChecked(mPreferences.getBoolean(KEY_POLITICS, false));
        sportsCb.setChecked(mPreferences.getBoolean(KEY_SPORTS, true));
        travelCb.setChecked(mPreferences.getBoolean(KEY_TRAVEL, true));
    }

    public List<String> getListOfCategories(){
        List<String> categories=new ArrayList<>();
        if (artsCb.isChecked()) {categories.add(artsCb.getText().toString());}
        if (businessCb.isChecked()) {categories.add(businessCb.getText().toString());}
        if (entrepreneursCb.isChecked()) {categories.add(entrepreneursCb.getText().toString());}
        if (politicsCb.isChecked()) {categories.add(politicsCb.getText().toString());}
        if (sportsCb.isChecked()) {categories.add(sportsCb.getText().toString());}
        if (travelCb.isChecked()) {categories.add(travelCb.getText().toString());}
        return categories;
    }

    public boolean searchNOk() {
        return  !(artsCb.isChecked() || businessCb.isChecked() || entrepreneursCb.isChecked() || politicsCb.isChecked() || sportsCb.isChecked() || travelCb.isChecked());
    }

    public void disableChange(Boolean bool){
        artsCb.setEnabled(!bool);
        businessCb.setEnabled(!bool);
        entrepreneursCb.setEnabled(!bool);
        politicsCb.setEnabled(!bool);
        sportsCb.setEnabled(!bool);
        travelCb.setEnabled(!bool);
    }

}
