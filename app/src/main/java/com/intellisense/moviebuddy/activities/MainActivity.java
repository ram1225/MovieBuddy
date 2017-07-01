package com.intellisense.moviebuddy.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.intellisense.moviebuddy.R;
import com.intellisense.moviebuddy.adapters.MoviesListAdapter;
import com.intellisense.moviebuddy.models.MovieItems;
import com.intellisense.moviebuddy.webservices.MoviesApiCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MoviesListAdapter mMoviesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);

        mMoviesListAdapter=new MoviesListAdapter(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMoviesListAdapter);

        initiateRetrofit();
    }

    private void initiateRetrofit() {
        MoviesApiCall moviesApiCall = MoviesApiCall.mRetrofit.create(MoviesApiCall.class);
        Call<MovieItems> mCall = moviesApiCall.getMoviesList();


        mCall.enqueue(new Callback<MovieItems>() {
            @Override
            public void onResponse(Call<MovieItems> call, Response<MovieItems> response) {
                mMoviesListAdapter.setData(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieItems> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
