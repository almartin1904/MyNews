package com.openclassroom.alice.mynews.Utils;



import com.openclassroom.alice.mynews.Model.ResultOfRequest.RequestResult;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


/**
 * Created by Alice on 10 December 2018.
 */
public interface NYTArticleService {
    public static final String APIKey = "11f7a4aedd9a475a9d285fed115121da";

    @GET("topstories/v2/home.json?api-key="+APIKey)
    Observable<RequestResult> getTopStories();

    @GET("mostpopular/v2/mostviewed/all-sections/30.json?api-key="+APIKey)
    Observable<RequestResult> getMostPopular();

    @GET("topstories/v2/sports.json?api-key="+APIKey)
    Observable<RequestResult> getSport();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
