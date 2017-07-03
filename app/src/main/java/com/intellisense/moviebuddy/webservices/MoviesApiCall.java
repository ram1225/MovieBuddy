package com.intellisense.moviebuddy.webservices;


import com.intellisense.moviebuddy.models.MovieItems;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Home on 7/1/2017.
 */

public interface MoviesApiCall {

    String mBaseUrl = "https://api.themoviedb.org/";

    @GET("3/movie/now_playing?page=1&api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
   // Call<MovieItems> getMoviesList();


    Observable<MovieItems> getMoviesList();

    //HTTP Client - OKHTTP 3
    OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
    OkHttpClient httpClient = builder.build();

    Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

}
