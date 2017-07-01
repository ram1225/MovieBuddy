package com.intellisense.moviebuddy.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.intellisense.moviebuddy.R;
import com.intellisense.moviebuddy.adapters.MoviesListAdapter;
import com.intellisense.moviebuddy.models.MovieItem;
import com.intellisense.moviebuddy.webservices.MoviesApiCall;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MoviesListAdapter mMoviesListAdappter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMoviesListAdappter);

        initiateRetrofit();
    }

    private void initiateRetrofit() {
        MoviesApiCall moviesApiCall = MoviesApiCall.mRetrofit.create(MoviesApiCall.class);
        Call<ResponseBody> mCall = moviesApiCall.getMoviesList();

        mCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.v("Response", response.body().string());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
       /* mCall.enqueue(new Callback<MovieItem>() {
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                Log.v("Response",response.toString());
            }

            @Override
            public void onFailure(Call<MovieItem> call, Throwable t) {
                t.printStackTrace();
            }
        }); */
    }
}
