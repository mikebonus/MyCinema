package com.sampler.mymovie_2020.viewmodels;

import android.graphics.Movie;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sampler.mymovie_2020.models.Result;
import com.sampler.mymovie_2020.repositories.MovieRepository;

// go to repository.java for step #85
// go to activity for step #86
public class MovieViewModel extends ViewModel {

    private static final String TAG = "MovieViewModel";

    private MovieRepository mMovieRepository;

    public MovieViewModel() {
        mMovieRepository = MovieRepository.getInstance();
    }

    public LiveData<Result> getMovie(){

        Log.d(TAG, "getMovie: step #84 ~ 85");
        return mMovieRepository.getMovie();
    }

    public void searchMovieById(String movie_id) {
        mMovieRepository.searchMovieById(movie_id);
    }

}
