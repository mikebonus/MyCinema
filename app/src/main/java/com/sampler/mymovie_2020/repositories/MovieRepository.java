package com.sampler.mymovie_2020.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.sampler.mymovie_2020.models.Result;
import com.sampler.mymovie_2020.requests.MovieApiClient;

import java.util.List;

// ★★★★★ step #16) Repository-class
// now finish up 'MovieListViewModel.java' for step #17
public class MovieRepository {

    private static final String TAG = "MovieRepository";

    private static MovieRepository instance;
    private MovieApiClient mMovieApiClient;

    // ★★★★★★★★★★ step #74 - PAGINATION (go below for step #75)
    private String mQuery;
    private int mPageNumber;


    public static MovieRepository getInstance(){
        if(instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository(){
        mMovieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<Result>> getMovies(){
        return mMovieApiClient.getMovies();
    }

    // ★★★★★★★★★★ step #83 - Secondary-REQUEST!
    public LiveData<Result> getMovie(){
        Log.d(TAG, "getMovie: step #83");
        return mMovieApiClient.getMovie();

        // now go to view-model.java for step #85
    }

    // step #83
    public void searchMovieById(String movie_id) {
        mMovieApiClient.searchMovieById(movie_id);
    }


    // ★★★★★ step #27) EXECUTING THE CORE-FUNCTION
    public void searchMoviesApi(String query, int pageNumber) {
        if(pageNumber == 0) {
            pageNumber = 1;
        }
        // ★★★★★★★★★★ step #75 - PAGINATION
        mQuery = query;
        mPageNumber = pageNumber;
        mMovieApiClient.searchMoviesApi(query, pageNumber);

        // go to ViewModel.java for step #28
    }

    // ★★★★★★★★★★ step #76 - PAGINATION
    // MovieListViewModel.java for step #77
    public void searchNextPage(){
        searchMoviesApi(mQuery, mPageNumber + 1);
    }

    //★★★★★ to implement 'cancel-request (step #71) --> go to apiclient...
    public void cancelRequest(){
        mMovieApiClient.cancelRequest();
    }

}
