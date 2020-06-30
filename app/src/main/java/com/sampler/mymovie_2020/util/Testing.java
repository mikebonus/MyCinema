package com.sampler.mymovie_2020.util;

import android.util.Log;

import com.sampler.mymovie_2020.models.Result;

import java.util.List;

public class Testing {

    private static final String TAG = "Testing";

    public static void printMovies(List<Result> list, String tag) {
        for (Result result : list) {
            Log.d(tag, "printMovies: " + result.getTitle());
        }
    }
}
