package com.sampler.mymovie_2020.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sampler.mymovie_2020.R;
import com.sampler.mymovie_2020.models.Result;

import java.util.ArrayList;
import java.util.List;

// ★★★★★ step #32)
// go to MovieViewHolder.java for step #33
public class MovieRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "MovieRecyclerAdapter";

    // ★★★★★ step #48) --> go below for step #49
    private static final int MOVIE_TYPE = 1;
    private static final int LOADING_TYPE = 2;

    // ★★★★★ step #57) --> coming from 'Constants.java'...
    private static final int CATEGORY_TYPE = 3;


    // fields
    private List<Result> mMovies;
    private OnMovieListener mOnMovieListener;

    // constructor:
    // ★★★★★ step #35)
    public MovieRecyclerAdapter(OnMovieListener mMovieListener) {
        this.mOnMovieListener = mMovieListener;
    }

    // method #1
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ★★★★★ step #36) EXECUTING THE CORE-FUNCTION
        View view = null;

        // ★★★★★ step #49) --> go below for step #50
        switch (viewType){
            case MOVIE_TYPE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie_list_item, parent, false);
                return new MovieViewHolder(view, mOnMovieListener);
            }

            case LOADING_TYPE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_list_item, parent, false);
                return new LoadingViewHolder(view);
            }

            // ★★★★★ step #58)
            case CATEGORY_TYPE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_list_item, parent, false);
                return new CategoryViewHolder(view, mOnMovieListener);
            }

            // ★★★★★ step #59) --> below for step #60
            default:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie_list_item, parent, false);
                return new MovieViewHolder(view, mOnMovieListener);
            }
        }
    }

    // method #2
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        // ★★★★★ step #50)
        int itemViewType = getItemViewType(position);

        // case 1:
        if (itemViewType == MOVIE_TYPE) {
            // ★★★★★ step #37) EXECUTING THE CORE-FUNCTION
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.cinemathree);

            Glide.with(holder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(mMovies.get(position)
                            .getPoster_path())
                    .into(((MovieViewHolder) holder).image);

            // now add the recyclerView to the 'activity_movie_list.xml' for step #37

            // ★★★★★ step #38) EXECUTING THE CORE-FUNCTION
            ((MovieViewHolder) holder).title.setText(mMovies.get(position).getTitle());
            ((MovieViewHolder) holder).release_date.setText(mMovies.get(position).getRelease_date());
//            ((MovieViewHolder) holder).popularity.setText(String.valueOf(Math.round(mMovies.get(position).getPopularity())));
            ((MovieViewHolder) holder).overview.setText(mMovies.get(position).getOverview());


        // case 2:
        } else if(itemViewType == CATEGORY_TYPE){
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            // ★★★★★ step #54)
            Uri path = Uri.parse("android:resource://com.sampler.mymovie_2020/drawable/"
                    + mMovies.get(position).getPoster_path());

            Glide.with(holder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(path)
                    .into(((CategoryViewHolder)holder).categoryImage);
            ((CategoryViewHolder)holder).categoryTitle.setText(mMovies.get(position).getTitle());
        }
    }

    // ★★★★★ step #51)
    @Override
    public int getItemViewType(int position) {

        // ★★★★★ step #55)
        if (mMovies.get(position).getVote_count() == -1) {
            return CATEGORY_TYPE;
        }

        else if(mMovies.get(position).getTitle().equals("LOADING...")){
            return LOADING_TYPE;
        }
        else if (position == mMovies.size() - 1
            && position != 0
            && !mMovies.get(position).getTitle().equals("EXHAUSTED...")) {
            return LOADING_TYPE;
        }
        else {
            return MOVIE_TYPE;
        }
    }

    public void displayLoading(){
        if(!isLoading()){
            Result result = new Result();
            result.setTitle("LOADING...");
            List<Result> loadingList = new ArrayList<>();
            loadingList.add(result);
            mMovies = loadingList;
            notifyDataSetChanged();
        }
    }


    // ★★★★★ step #53)
    // now go to 'MovieListActivity.java' to run this method... under 'initSearchView()'...
    private boolean isLoading(){
        if(mMovies != null){
            if(mMovies.size() > 0){
                if(mMovies.get(mMovies.size() - 1)
                        .getTitle()
                        .equals("LOADING...")){      // currently in loading-mode...
                    return true;
                }
            }
        }
        return false;       // if NOT loading-mode... (if the last-list-item in the list is in the LOADING-MODE)....

        // now build 'layout_category_list_item.xml'...
    }

//    // ★★★★★ step #56)
//    public void displaySearchCategories(){
//        List<Result> categories = new ArrayList<>();
//        for(int i = 0; i< Constants.DEFAULT_SEARCH_CATEGORIES.length; i++){
//            Result result = new Result();
//            result.setTitle(Constants.DEFAULT_SEARCH_CATEGORIES[i]);
//            result.setPoster_path(Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i]);
//            result.setVote_count(-1);
//            categories.add(result);
//        }
//        mMovies = categories;
//        notifyDataSetChanged();
//
//        // go to MoveListViewModel.java for step #57
//    }


    // method #3
    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }

    // SETTERS... (list of movies will be set through this method, NOT through the CONSTRUCTOR)
    public void setMovies(List<Result> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    // ★★★★★★★★★★ step #75 - INTENT-PASSER!
    // GO TO MovieListActivity.java for step #76
    public Result getSelectedMovie(int position){
        if (mMovies != null) {
            if (mMovies.size() > 0) {
                return mMovies.get(position);
            }
        }
        return null;
    }


}
