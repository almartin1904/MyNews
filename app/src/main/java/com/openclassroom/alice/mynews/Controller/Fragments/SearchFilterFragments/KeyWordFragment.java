package com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.openclassroom.alice.mynews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeyWordFragment extends Fragment {
    private static final String KEY_KEY_WORD = "KEY_KEY_WORD";
    @BindView(R.id.fragment_key_word_EditTxt) EditText keyWordTxt;

    public KeyWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_key_word, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public String getKeyKeyWord(){
        return keyWordTxt.getText().toString();
    }

    public void saveKeyWordState(SharedPreferences mPreferences){
        mPreferences.edit().putString(KEY_KEY_WORD, keyWordTxt.getText().toString()).apply();
    }

    public void displayKeyWordState(SharedPreferences mPreferences){
        keyWordTxt.setText(mPreferences.getString(KEY_KEY_WORD, ""));
        keyWordTxt.setSelection(keyWordTxt.getText().length());
    }

    public boolean searchNOk(){
        return (keyWordTxt.getText().toString().equals(""));
    }

    public void setEnabledKeyWord(final Boolean bool){
        keyWordTxt.setEnabled(!bool);
    }
}
