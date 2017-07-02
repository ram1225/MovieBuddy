package com.intellisense.moviebuddy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intellisense.moviebuddy.R;
import com.intellisense.moviebuddy.models.MovieItems;
import com.intellisense.moviebuddy.models.ResultsItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Home on 7/1/2017.
 */

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder> {
    private Context context;
    private List<ResultsItem> mMovieItems = new ArrayList<>();

    public MoviesListAdapter(Context context) {
        this.context = context;
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_name)
        public TextView mMovieTitle;
        @BindView(R.id.movie_image)
        public ImageView mMoviePosterPath;

        public MoviesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            // mMovieTitle = (TextView) view.findViewById(R.id.movie_name);
            //mMoviePosterPath = (ImageView) view.findViewById(R.id.movie_image);
        }
    }

    public void setData(List<ResultsItem> mMovieItems) {
        this.mMovieItems = mMovieItems;
        notifyDataSetChanged();
    }

    @Override
    public MoviesListAdapter.MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mMovieItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MoviesViewHolder(mMovieItemView);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        ResultsItem mMovieItem = mMovieItems.get(position);
        holder.mMovieTitle.setText(mMovieItem.getTitle());
        Picasso.with(this.context).load("http://image.tmdb.org/t/p/w342/" + mMovieItem.getPosterPath() + "?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed").into(holder.mMoviePosterPath);
    }

    @Override
    public int getItemCount() {
        return mMovieItems.size();
    }
}
