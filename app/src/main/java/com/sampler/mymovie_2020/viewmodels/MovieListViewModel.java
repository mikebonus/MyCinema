package com.sampler.mymovie_2020.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sampler.mymovie_2020.models.Result;
import com.sampler.mymovie_2020.repositories.MovieRepository;
import com.sampler.mymovie_2020.util.VerticalSpacingItemDecorator;

import java.util.List;

// ★★★★★ step #12)
public class MovieListViewModel extends ViewModel {

    private MovieRepository mMovieRepository;

    // ★★★★★ step #57)
    private boolean mIsViewingMovies;

    //★★★★★ to implement 'cancel-request (step #65)
    private boolean mIsPerformgingQuery;


    // Constructor: retrieve + hold + displaying the MOVIES displayed in the application
    // keeps an updated list of the movies.
    public MovieListViewModel() {
        // ★★★★★ step #58)
//        mIsViewingMovies = false;           // should be viewing the 'categories...'
        mMovieRepository = MovieRepository.getInstance();

        //★★★★★ to implement 'cancel-request (step #66)
        mIsPerformgingQuery = false;
    }

    // ★★★★★ step #17) Repository-class
    public LiveData<List<Result>> getMovies(){
        return mMovieRepository.getMovies();
        // now go to MovieApiClient.java for step #18
    }
    // go to MovieListActivity for declaring + instantiating the ViewModel (for step #13)


    // ★★★★★ step #28) EXECUTING THE CORE-FUNCTION
    public void searchMoviesApi(String query, int pageNumber) {
        mIsViewingMovies = true;            // cause if you are searching, that means you are viewing the MOVIES...

        //★★★★★ to implement 'cancel-request (step #68) --- now jump to
        mIsPerformgingQuery = true;

        mMovieRepository.searchMoviesApi(query, pageNumber);
    }

    // now go to MovieListactivity.java for step #29



    // ★★★★★★★★★★ step #77 - PAGINATION
    // (go to activity for 78)
    public void searchNextPage(){
        if(!mIsPerformgingQuery && mIsViewingMovies) {
            mMovieRepository.searchNextPage();
        }
    }


    // Getter and Setter of Boolean...
    // ★★★★★ step #59)
    public boolean isViewingMovies(){
        return mIsViewingMovies;
    }

    // ★★★★★ step #60)
    // go to Activity for step #61
    public void setIsViewingRecipes(boolean isViewingMovies){
        mIsViewingMovies = isViewingMovies;
    }

    //★★★★★ to implement 'cancel-request (step #67)
    public void setmIsPerformgingQuery(Boolean isPerformgingQuery) {
        mIsPerformgingQuery = isPerformgingQuery;
    }

    public boolean isPerformingQuery (){
        return mIsPerformgingQuery;
    }


    // ★★★★★ step #64) -->
    // go back to 'MovieListActivity.java' for step #65
    public boolean onBackPressed(){

//        //★★★★★ to implement 'cancel-request (step #69)
//        if (mIsPerformgingQuery) {
//            // cancel the query... (send msg to repo --> client --> cancel the request)
//            mMovieRepository.cancelRequest();
//            mIsPerformgingQuery = false;
//        }

        if (mIsViewingMovies) {
            mIsViewingMovies = false;
            return false;
        }
        return true;
    }
    // now go to movieListActivity for step #70

    // now go to movieListActivity to remove 'FOCUS'... for step #72

}
