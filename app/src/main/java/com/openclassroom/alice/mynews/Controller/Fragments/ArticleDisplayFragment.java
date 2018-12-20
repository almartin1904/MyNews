package com.openclassroom.alice.mynews.Controller.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.openclassroom.alice.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleDisplayFragment extends Fragment {

    @BindView(R.id.webview) WebView mWebView;

    public ArticleDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_display, container, false);
        ButterKnife.bind(this, view);
        if (getActivity()!=null){
            Intent i=getActivity().getIntent();
            String Url = (String) i.getSerializableExtra(String.valueOf(R.string.URL));
            mWebView.loadUrl(Url);
        }
        return view;
    }

}
