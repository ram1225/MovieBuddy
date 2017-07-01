package com.intellisense.moviebuddy.webservices;


import com.intellisense.moviebuddy.models.MovieItem;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Home on 7/1/2017.
 */

public interface MoviesApiCall {

    String mBaseUrl = "https://api.themoviedb.org/";

    @GET("3/movie/now_playing?page=1&api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Call<ResponseBody> getMoviesList();


    Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .build();

}
