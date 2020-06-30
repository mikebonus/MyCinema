package com.sampler.mymovie_2020.requests;

import com.sampler.mymovie_2020.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// SINGLETON-PATTERN for creating a single instance of this class
// ★★★★★ step #5) REST-API
// Go to MovieApi.java for creating methods to communicate with the API (for step #5)
public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    // Create an instance of 'Retrofit'
    // (only a single instance would be used)
    private static Retrofit retrofit = retrofitBuilder.build();

    // Create an instance of MovieApi (returns a static Retrofit-instance)
    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    // Getter of the API-instance (returns a static 'MovieApi'-instance which returns a static Retrofit-instance)
    public static MovieApi getMovieApi(){
        return movieApi;
    }
}