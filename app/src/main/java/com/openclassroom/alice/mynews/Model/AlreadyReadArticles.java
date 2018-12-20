package com.openclassroom.alice.mynews.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alice on 18 December 2018.
 */
public class AlreadyReadArticles {

    private static final String PREF_KEY_SAVE_ARTICLE = "PREF_KEY_SAVE_ARTICLE";
    private static final String LIST_OF_ARTICLES = "LIST_OF_ARTICLES";

    private SharedPreferences mPreferences;

    //------------------
    //pattern singleton
    //------------------

    private AlreadyReadArticles(Context context){
        mPreferences = context.getSharedPreferences(PREF_KEY_SAVE_ARTICLE, Context.MODE_PRIVATE);
    }

    private static  AlreadyReadArticles INSTANCE = null;

    public static synchronized AlreadyReadArticles getInstance(Context context)
    {
        if (INSTANCE == null)
        {   INSTANCE = new AlreadyReadArticles(context);
        }
        return INSTANCE;
    }

    //---------------------------
    //Save and load the articles
    //---------------------------


    public List<String> loadArticles() {
        String articlesAsString = mPreferences.getString(LIST_OF_ARTICLES,null);
        List<String> articlesAsList;

        if (articlesAsString == null){
            articlesAsList = new ArrayList<>();
        } else {
            Type targetClassType = new TypeToken<ArrayList<String>>() { }.getType();
            articlesAsList = new Gson().fromJson(articlesAsString, targetClassType);
        }
        return articlesAsList;
    }

    public void saveArticles(List<String> articles){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        mPreferences.edit().putString(LIST_OF_ARTICLES, gson.toJson(articles)).apply();
    }

    public boolean hasAlreadyBeenRead(String newTitle){
        List<String> readTitles = loadArticles();
        return readTitles.contains(newTitle);
    }

    public void addNewTitleToList(String newTitle){
        List<String> readTitles = loadArticles();
        readTitles.add(newTitle);
        saveArticles(readTitles);
    }

    public int sizeList(){
        return loadArticles().size();
    }
}
