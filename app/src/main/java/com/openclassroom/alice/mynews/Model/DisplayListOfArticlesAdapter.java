package com.openclassroom.alice.mynews.Model;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.openclassroom.alice.mynews.Controller.Fragments.DisplayListOfArticleFragment;

/**
 * Created by Alice on 19 December 2018.
 */
public class DisplayListOfArticlesAdapter extends FragmentPagerAdapter {

    public DisplayListOfArticlesAdapter(FragmentManager mgr, Context context) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return(3); // 3 - Number of page to show
    }

    @Override
    public Fragment getItem(int position) {
        return (DisplayListOfArticleFragment.newInstance(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page "+position;
    }

}
