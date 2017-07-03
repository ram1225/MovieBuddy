package com.intellisense.moviebuddy.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.intellisense.moviebuddy.R;
import com.intellisense.moviebuddy.adapters.MoviesListAdapter;
import com.intellisense.moviebuddy.models.MovieItems;
import com.intellisense.moviebuddy.webservices.MoviesApiCall;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.movies_recycler_view)
     RecyclerView mRecyclerView;
     MoviesListAdapter mMoviesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //   mRecyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);

        mMoviesListAdapter = new MoviesListAdapter(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMoviesListAdapter);

        initiateRetrofit();
    }

    private void initiateRetrofit() {
        MoviesApiCall moviesApiCall = MoviesApiCall.mRetrofit.create(MoviesApiCall.class);
        /*Observable<MovieItems> mCall = */

                                                                        moviesApiCall.getMoviesList()
                                                                        .subscribeOn(Schedulers.io())
                                                                        .map(movieItems -> movieItems.getResults())
                                                                        .observeOn(AndroidSchedulers.mainThread())
                                                                        .subscribe(resultsItems -> mMoviesListAdapter.setData(resultsItems),
                                                                                                 throwable -> throwable.printStackTrace(),
                                                                                                () -> {});



      /*
      This is without RxJava2 for Retrofit asynchronous call

      mCall.enqueue(new Callback<MovieItems>() {
            @Override
            public void onResponse(Call<MovieItems> call, Response<MovieItems> response) {
                mMoviesListAdapter.setData(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieItems> call, Throwable t) {
                t.printStackTrace();
            }
        });*/

      /*mCall.subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(MovieItems -> {
                  Log.v("Test",MovieItems.getResults().toString());
                  //mMoviesListAdapter.setData(ResultItem);
              });*/

        /*mCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())*/

    }
}
