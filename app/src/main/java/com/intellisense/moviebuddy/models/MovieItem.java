package com.intellisense.moviebuddy.models;

/**
 * Created by Home on 7/1/2017.
 */

public class MovieItem {

    public String movieTitle;
    public String moviePosterPath;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        this.moviePosterPath = moviePosterPath;
    }
}
