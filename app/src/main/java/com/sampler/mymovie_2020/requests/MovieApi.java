package com.sampler.mymovie_2020.requests;

import com.sampler.mymovie_2020.requests.responses.MovieResponse;
import com.sampler.mymovie_2020.requests.responses.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

// ★★★★★ step #6) REST-API &&
// ★★★★★ returned in step #9
// Contains methods to communicate with the API
// Go to Movie.java for step #7
public interface MovieApi {

    // SEARCH
    // Retrofit forces you to separately manage the base-url && additional url...(like this)...
    // each '@'-annotation appends '&' symbol to make a request...
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page
    );

    // GET MOVIE REQUEST
    // (first-param seems to be null according to the request-url, but if error, try 'id')...
    @GET("/3/movie/{movie_id}")
    Call<MovieResponse> getMovie(
            @Path("movie_id") String movie_id,
            @Query("api_key") String key
    );

    // https://api.themoviedb.org/3/movie/315635?api_key=d22e1216a7fca6afa3ba896f72cd36f4


    // ★★★★★ now go to MovieListActivity.java for step #10
}
