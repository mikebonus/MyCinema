package com.sampler.mymovie_2020.adapters;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.sampler.mymovie_2020.R;

// ★★★★★ step #33) EXECUTING THE CORE-FUNCTION
// create OnMovieListener for step #34
public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String TAG = "MovieViewHolder";

    // Fields
    TextView title;
    TextView release_date;
    TextView popularity;
    ImageView image;

    // overview
    TextView overview;


    // Interface
    OnMovieListener onMovieListener;

    // Constructor
    public MovieViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);

        // init interface
        this.onMovieListener = onMovieListener;

        title = itemView.findViewById(R.id.movie_title);
        release_date = itemView.findViewById(R.id.movie_release_date);
        popularity = itemView.findViewById(R.id.movie_popularity);
        image = itemView.findViewById(R.id.movie_image);

        overview = itemView.findViewById(R.id.movie_overview);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        onMovieListener.onMovieClick(getAdapterPosition());
    }

    // return to MovieRecyclerAdapter.java for next step
}
