package com.sampler.mymovie_2020;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sampler.mymovie_2020.adapters.MovieRecyclerAdapter;
import com.sampler.mymovie_2020.adapters.OnMovieListener;
import com.sampler.mymovie_2020.models.Result;
import com.sampler.mymovie_2020.repositories.MovieRepository;
import com.sampler.mymovie_2020.util.Testing;
import com.sampler.mymovie_2020.util.VerticalSpacingItemDecorator;
import com.sampler.mymovie_2020.viewmodels.MovieListViewModel;

import java.io.Serializable;
import java.util.List;

// ★★★★★ step #3) extend 'BaseActivity' which extends 'AppCompatActivity'...
// Go to Constants for step #4
public class MovieListActivity extends BaseActivity
        implements OnMovieListener {

    private static final String TAG = "MovieListActivity";

    // ★★★★★ step #38)
    private RecyclerView mRecyclerView;
    private MovieRecyclerAdapter mMovieRecyclerAdapter;

    // ★★★★★ step #13) Declaring view-Model
    private MovieListViewModel mMovieListViewModel;

    //★★★★★ to implement 'cancel-request' - focus-REMOVAL'(step #72)
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        // ★★★★★ step #39)
        mRecyclerView = findViewById(R.id.movie_list);

        //★★★★★ to implement 'cancel-request (step #73)
        mSearchView = findViewById(R.id.search_view);

        // ★★★★★ step #14) Instantiating view-Model
        mMovieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);


        initRecyclerView();
        // ★★★★★ step #16) subscribe()
        subscribeObservers();

        // ★★★★★ step #44)
        initSearchView();
//        testRetrofitRequest();

        // now let us build the HORIZONTAL-PROGRESS-BAR-for-LOADING...
        // go to 'HorizontalDottedProgress.java' for step #45


        // ★★★★★ step #60) --> coming from 'MovieListViewModel' to
        // determine when to display the CATEGORY or 'LIST'...
        // go below for step #61
        if (!mMovieListViewModel.isViewingMovies()) {        // if viewing the categories....
            // step #62 (display search-categories....)
            displaySearchCategories();

        }
    }

    // All the objects / variables that change are OBSERVED
    // lIST<mOVIE> is the data... we will OBSERVE the Live-Data...
    // (ie. data added/removed/updated. etc), this method gets triggered
    // ★★★★★ step #15) subscribe()
    private void subscribeObservers(){

        mMovieListViewModel.getMovies().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                // ★★★★★ step #31) EXECUTING THE CORE-FUNCTION
                if(results != null) {           // ★★★★★ step #65)      -----> now go to MovieListViewModel to implement 'cancel-Request'...
                    if(mMovieListViewModel.isViewingMovies()) {
                        Testing.printMovies(results, "movies TEST:");

                        //★★★★★ to implement 'cancel-request (step #70) - b/c if we retrieving anything, that means the query is complete...
                        // (now go to Repo)
                        mMovieListViewModel.setmIsPerformgingQuery(false);

                        // ★★★★★ step #41)
                        // After this, make sure to 'add network-config' b/c glide does not properly render images for API-28 or above...
                        // go to xml --> network_security_config (for step #42)
                        mMovieRecyclerAdapter.setMovies(results);

                        //Now go to 'activity_movie_list.xml for COLLAPSING TOOL-BAR (for step #43)
                    }
                }
                // now work on the front-UI stuff (for step #32)
                // go to MovieRecyclerAdapter.java for step #32
            }
        });

        // Now create repository.java for step #16
    }

    // ★★★★★ step #40)
    private void initRecyclerView(){
        mMovieRecyclerAdapter = new MovieRecyclerAdapter(this);

        // ★★★★★ step #62) -->
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(30);
        mRecyclerView.addItemDecoration(itemDecorator);

        mRecyclerView.setAdapter(mMovieRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // now let us work on the back-nav-button
        // go to bottom for 'onBackPressed()' for step #63


        // ★★★★★★★★★★ step #73 - PAGINATION
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!mRecyclerView.canScrollVertically(1)) {  // if at the very bottom of the list
                    // search the next page --> go to repo... for step #74
                    mMovieListViewModel.searchNextPage();
                }

            }
        });

    }


    // ★★★★★ step #29) EXECUTING THE CORE-FUNCTION
    public void searchMoviesApi(String query, int pageNumber) {
        mMovieListViewModel.searchMoviesApi(query, pageNumber);
    }

    // ★★★★★ step #43)
    private void initSearchView(){
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // when submitted...
            @Override
            public boolean onQueryTextSubmit(String query) {

                // ★★★★★ step #54) - coming from 'MovieRecyclerAdapter.java'....
                mMovieRecyclerAdapter.displayLoading();

                mMovieListViewModel.searchMoviesApi(query, 1);

                //★★★★★ to implement 'cancel-request' - focus-REMOVAL'(step #73)
                mSearchView.clearFocus();

                return false;
            }

            // for every single digit being inserted...
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // call this method above for step #44...
    }


    // ★★★★★ step #30) EXECUTING THE CORE-FUNCTION
    private void testRetrofitRequest(){
        searchMoviesApi("spiderman", 1);        // either 0 or 1
    }

    // ★★★★★★★★★★ step #76 - INTENT-PASSER!
    @Override
    public void onMovieClick(int position) {
        Log.d(TAG, "onMovieClick: intent is fired here.....---------------------------");
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra("movie", (mMovieRecyclerAdapter.getSelectedMovie(position)));
        startActivity(intent);

        // now go to MovieActivity.java for step #77 
    }

    @Override
    public void onCategoryClick(String category) {
        mMovieRecyclerAdapter.displayLoading();
        mMovieListViewModel.searchMoviesApi(category, 1);

        //★★★★★ to implement 'cancel-request' - focus-REMOVAL'(step #73)
        mSearchView.clearFocus();
    }

    // ★★★★★ step #61) -->
    private void displaySearchCategories(){
        mMovieListViewModel.setIsViewingRecipes(false);
//        mMovieRecyclerAdapter.displaySearchCategories();
    }

    // go to VerticalSpacingItemDecorator for step #62

    // ★★★★★ step #63) --> for now go to MovieListViewModel.java for step #64
    @Override
    public void onBackPressed() {
        if (mMovieListViewModel.onBackPressed()) {
            super.onBackPressed();
        } else {
            displaySearchCategories();
        }
    }
}
