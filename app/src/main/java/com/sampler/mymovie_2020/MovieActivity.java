package com.sampler.mymovie_2020;

import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sampler.mymovie_2020.models.Result;
import com.sampler.mymovie_2020.viewmodels.MovieViewModel;

// ★★★★★★★★★★ step #74 - LAYOUT-SETUP (for SECONDARY-ACTIVITY)
// GO TO 'RecyclerAdapter.java' for step #75
public class MovieActivity extends BaseActivity {

    private static final String TAG = "RecipeActivity";

    // UI components
    private AppCompatImageView mRecipeImage;
    private TextView mRecipeTitle;
    private TextView mRecipeRank;
    private LinearLayout mRecipeIngredientsContainer;
    private ScrollView mScrollView;

    // ★★★★★★★★★★  step #86 (single-request)
    private MovieViewModel mMovieViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mRecipeImage = findViewById(R.id.recipe_image);
        mRecipeTitle = findViewById(R.id.recipe_title);
        mRecipeRank = findViewById(R.id.recipe_social_score);
        mRecipeIngredientsContainer = findViewById(R.id.ingredients_container);
        mScrollView = findViewById(R.id.parent);

        // ★★★★★★★★★★  step #87 (single-request)
        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        Log.d(TAG, "onCreate: step #87..........");

        showProgressBar(true);
        subscribeObservers();
        getIncomingIntent();
    }

    // ★★★★★★★★★★ step #77 - INTENT-PASSER! (ie. coming from 'MovieListActivity.java)...
    // NOW LET US DO THE 'SINGULAR-REQUEST' for MOVIE-ACTIVITY... go to ApiClient.java for step #78....
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: intent has arrived here.... SPIDERMAN IS HERE TAHAHAHAHA----------------------------");

        if (getIntent().hasExtra("movie")){
            Result result = getIntent().getParcelableExtra("movie");
            Log.d(TAG, "getIncomingIntent: " + result.getTitle() + "----------------------------------------------------");
            mMovieViewModel.searchMovieById(String.valueOf(result.getId()));
        }
    }

    // ★★★★★★★★★★  step #88 (single-request)
    private void subscribeObservers(){

        Log.d(TAG, "onCreate: step #88 finally.............");

        mMovieViewModel.getMovie().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(@Nullable Result result) {

                Log.d(TAG, "onChanged: can you see this????????????");

                if(result != null){

                    Log.d(TAG, "onChanged: everything is working fine up to this point-----------------------------------------");
                    Log.d(TAG, "onChanged: everything is working fine up to this point-----------------------------------------");

                }
            }
        });

    }

//    private void displayErrorScreen(String errorMessage){
//        mRecipeTitle.setText("Error retrieveing recipe...");
//        mRecipeRank.setText("");
//        TextView textView = new TextView(this);
//        if(!errorMessage.equals("")){
//            textView.setText(errorMessage);
//        }
//        else{
//            textView.setText("Error");
//        }
//        textView.setTextSize(15);
//        textView.setLayoutParams(new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
//        ));
//        mRecipeIngredientsContainer.addView(textView);
//
//        RequestOptions requestOptions = new RequestOptions()
//                .placeholder(R.drawable.ic_launcher_background);
//
//        Glide.with(this)
//                .setDefaultRequestOptions(requestOptions)
//                .load(R.drawable.ic_launcher_background)
//                .into(mRecipeImage);
//
//        showParent();
//        showProgressBar(false);
//    }
//
//    private void setRecipeProperties(Result recipe){
//        if(recipe != null){
//            RequestOptions requestOptions = new RequestOptions()
//                    .placeholder(R.drawable.ic_launcher_background);
//
//            Glide.with(this)
//                    .setDefaultRequestOptions(requestOptions)
//                    .load(recipe.getPoster_path())
//                    .into(mRecipeImage);
//
//            mRecipeTitle.setText(recipe.getTitle());
//            mRecipeRank.setText(String.valueOf(Math.round(recipe.getVote_count())));
//
//            mRecipeIngredientsContainer.removeAllViews();
//            for(String ingredient: recipe.getIngredients()){
//                TextView textView = new TextView(this);
//                textView.setText(ingredient);
//                textView.setTextSize(15);
//                textView.setLayoutParams(new LinearLayout.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
//                ));
//                mRecipeIngredientsContainer.addView(textView);
//            }
//        }
//
//        showParent();
//        showProgressBar(false);
//    }
//
//    private void showParent(){
//        mScrollView.setVisibility(View.VISIBLE);
//    }
}














