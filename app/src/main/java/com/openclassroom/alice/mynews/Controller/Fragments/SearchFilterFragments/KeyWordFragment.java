package com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.openclassroom.alice.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KeyWordFragment extends Fragment {

    private static final String KEY_KEY_WORD = "KEY_KEY_WORD";

    @BindView(R.id.fragment_key_word_EditTxt) EditText mKeyWordTxt;

    public KeyWordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_key_word, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public String getKeyKeyWord(){
        return mKeyWordTxt.getText().toString();
    }

    public void saveKeyWordState(SharedPreferences mPreferences){
        mPreferences.edit().putString(KEY_KEY_WORD, mKeyWordTxt.getText().toString()).apply();
    }

    public void displayKeyWordState(SharedPreferences mPreferences){
        mKeyWordTxt.setText(mPreferences.getString(KEY_KEY_WORD, ""));
        mKeyWordTxt.setSelection(mKeyWordTxt.getText().length());
    }

    public boolean searchNOk(){
        return (mKeyWordTxt.getText().toString().equals(""));
    }

    public void setEnabledKeyWord(final Boolean bool){
        mKeyWordTxt.setEnabled(!bool);
    }
}
