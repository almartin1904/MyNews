package com.openclassroom.alice.mynews.Controller.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassroom.alice.mynews.Model.DisplayListOfArticlesAdapter;
import com.openclassroom.alice.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayListOfArticleFragment extends Fragment {

    private static final String KEY_POSITION="position";

    public DisplayListOfArticleFragment() { }

    public static DisplayListOfArticleFragment newInstance(int position) {
        DisplayListOfArticleFragment frag = new DisplayListOfArticleFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);
        return(frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_display_list_of_article, container, false);
        TextView textView = result.findViewById(R.id.fragment_displayListOfArticle_textview);

        int position = getArguments().getInt(KEY_POSITION, -1);

        textView.setText("Page numéro "+ position);

        return result;
    }

}
