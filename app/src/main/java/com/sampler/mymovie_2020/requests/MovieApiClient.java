package com.sampler.mymovie_2020.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sampler.mymovie_2020.AppExecutors;
import com.sampler.mymovie_2020.models.Result;
import com.sampler.mymovie_2020.requests.responses.MovieResponse;
import com.sampler.mymovie_2020.requests.responses.MovieSearchResponse;
import com.sampler.mymovie_2020.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.sampler.mymovie_2020.util.Constants.NETWORK_TIMEOUT;

// ★★★★★ step #18) Repository-class
public class MovieApiClient {

    private static final String TAG = "MovieApiClient";
    private static MovieApiClient instance;
    private MutableLiveData<List<Result>> mMovies;

    // ★★★★★ step #24)
    private RetrieveMoviesRunnable mRetrieveMoviesRunnable;

    // ★★★★★ step #100) - NETWORK_TIMEOUT




    // ★★★★★★★★★★ step #78 - Secondary-REQUEST!
    // (ie. coming from 'MovieListActivity.java)...
    private MutableLiveData<Result> mMovie;
    private RetrieveMovieRunnable mRetrieveMovieRunnable;

    public static MovieApiClient getInstance(){
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient(){
        mMovies = new MutableLiveData<>();

        // ★★★★★★★★★★ step #79 - Secondary-REQUEST! (go below for #80)
        mMovie = new MutableLiveData<>();

        Log.d(TAG, "step #79 is called here.....CONFIRMED ");
    }

    public LiveData<List<Result>> getMovies(){
        return mMovies;
    }


     // ★★★★★★★★★★ step #81 - Secondary-REQUEST! (go below for individual single-movie-request)
    public MutableLiveData<Result> getMovie(){
        return mMovie;
    }



    // Now create a class that generates a background-thread (for step #19)
    // all the requests need to be performed on a background thread


    // ★★★★★ step #20) Use the 'networkIO' thing we created to make a network request!
    // go to constants...for NETWORK-TIMEOUT SETTER of 3000 ms
    public void searchMoviesApi(String query, int pageNumber){

        // ★★★★★ step #25)
        // if query is executed in the past, set it null, and instantiate a new one...
        if (mRetrieveMoviesRunnable != null) {
            mRetrieveMoviesRunnable = null;
        }
        mRetrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        // ★★★★★ step #26)
        // now go to Repo for step #27
        final Future handler =
                AppExecutors
                        .getInstance()
                        .networkIO()
                        .submit(mRetrieveMoviesRunnable);

        AppExecutors
                .getInstance()
                .networkIO()
                .schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know it is timed-out...
                handler.cancel(true);       // cancel the request if the time-out occurs...
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }



    // ★★★★★★★★★★ step #82 - Secondary-REQUEST!
    public void searchMovieById(String movie_id) {
        if (mRetrieveMovieRunnable != null) {
            mRetrieveMovieRunnable = null;
        }

        Log.d(TAG, "searchMovieById: step #82-----------------------------------------");
        
        mRetrieveMovieRunnable = new RetrieveMovieRunnable(movie_id);

        Log.d(TAG, "searchMovieById: step #82.555555555-----------------------------------------");

        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveMovieRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know its timed out...
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT,TimeUnit.MILLISECONDS);

        // go to repository.java for step #83
    }








    // ★★★★★ step #23)
    // search-query....
    private class RetrieveMoviesRunnable
            implements Runnable {

        // fields
        private String query;
        private int pageNumber;
        boolean cancelRequest;      // to stop the runnable manually later...

        // Constructor
        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {

            try {
                Response response = getMovies(query, pageNumber).execute();

                if (cancelRequest) {
                    return;
                }

                if (response.code() == 200) {
                    ArrayList<Result> list = new ArrayList<>(((MovieSearchResponse)response.body()).getResults());

                    if (pageNumber == 1) {
                        mMovies.postValue(list);
                    }
                    else {
                        List<Result> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }

                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    mMovies.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }
        }

        private Call<MovieSearchResponse> getMovies(String query, int pageNumber) {
            return ServiceGenerator
                    .getMovieApi()
                    .searchMovie(
                    Constants.API_KEY,
                    query,
                    String.valueOf(pageNumber)
            );
        }

        private void cancelRequest(){
            Log.d(TAG, "cancelRequest: cancelling the search reqeust");
            cancelRequest = true;
        }
    }






    // ★★★★★★★★★★ step #80 - Secondary-REQUEST!
    private class RetrieveMovieRunnable
            implements Runnable {

        // fields
        private String movie_id;
        boolean cancelRequest;

        // Constructor
        public RetrieveMovieRunnable(String movie_id) {
            this.movie_id = movie_id;
            cancelRequest = false;

            Log.d(TAG, "Secondary-request for step #80 ----------------------------------");
        }

        @Override
        public void run() {
            try {
                Response response = getMovie(movie_id).execute();

                if (cancelRequest) {
                    return;
                }

                Log.d(TAG, "run: this is step #81 (SHOWN IN THE CONSOLE) -------------------------------------------------------------------");
                Log.d(TAG, "run: this is step #81.333333 (SHOWN IN THE CONSOLE) -------------------------------------------------------------------");
                Log.d(TAG, "run: this is step #81.55555 (SHOWN IN THE CONSOLE) -------------------------------------------------------------------");

                // ★★★★★★★★★★ step #81 - Secondary-REQUEST!
                if (response.code() == 200) {

                    Log.d(TAG, "run: this is step #81.77777 (NOT SHOWN IN THE CONSOLE)-------------------------------------------------------------------");

                    List<Result> myList = new ArrayList<>();
                    for (int i = 0; i < myList.size(); i++) {
                        myList.get(i).setId(((MovieResponse)response.body()).getId());
                        myList.get(i).setPoster_path(((MovieResponse)response.body()).getPoster_path());
                        myList.get(i).setTitle(((MovieResponse)response.body()).getTitle());
                        myList.get(i).setOverview(((MovieResponse)response.body()).getOverview());
                        myList.get(i).setRelease_date(((MovieResponse)response.body()).getRelease_date());
                    }

                    mMovie.postValue((Result) myList);

                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    mMovie.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mMovie.postValue(null);
            }
        }

        private Call<MovieResponse> getMovie(String movie_id) {
            return ServiceGenerator
                    .getMovieApi().getMovie(
                            Constants.API_KEY,
                            movie_id
            );
        }

        private void cancelRequest(){
            Log.d(TAG, "cancelRequest: cancelling the search reqeust");
            cancelRequest = true;
        }
    }





    //★★★★★ to implement 'cancel-request (step #72)
    public void cancelRequest(){
        if(mRetrieveMoviesRunnable != null){
            mRetrieveMoviesRunnable.cancelRequest();
        }

        if(mRetrieveMovieRunnable != null){
            Log.d(TAG, "cancelRequest: ------------------------------------------------step eighty-something..-------------------");
            mRetrieveMoviesRunnable.cancelRequest();
        }
    }

}
