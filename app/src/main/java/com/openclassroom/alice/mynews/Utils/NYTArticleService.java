package com.openclassroom.alice.mynews.Utils;



import com.openclassroom.alice.mynews.Model.ResultOfRequest.RequestResult;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Alice on 10 December 2018.
 */
public interface NYTArticleService {
    public static final String mAPIKey = "11f7a4aedd9a475a9d285fed115121da";

    @GET("topstories/v2/home.json?api-key="+mAPIKey)
    Observable<RequestResult> getTopStories();

    @GET("mostpopular/v2/mostviewed/all-sections/30.json?api-key="+mAPIKey)
    Observable<RequestResult> getMostPopular();

    @GET("topstories/v2/sports.json?api-key="+mAPIKey)
    Observable<RequestResult> getSport();

    @GET("search/v2/articlesearch.json?facet_field=source&api-key=11f7a4aedd9a475a9d285fed115121da")
    Observable<RequestResult> getSearchArticle(@Query("q") String queryTerm, @Query("fq") String section, @Query("begin_date") String beginDate, @Query("end_") String endDate);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
