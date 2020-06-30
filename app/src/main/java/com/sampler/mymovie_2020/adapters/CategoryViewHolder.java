package com.sampler.mymovie_2020.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sampler.mymovie_2020.R;

import de.hdodenhof.circleimageview.CircleImageView;

// ★★★★★ step #55)
// Go to Constants.java for 'sample categories' for step #56)...

public class CategoryViewHolder
        extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    CircleImageView categoryImage;
    TextView categoryTitle;
    OnMovieListener listener;

    public CategoryViewHolder(@NonNull View itemView, OnMovieListener listener) {
        super(itemView);

        this.listener = listener;
//        categoryImage = itemView.findViewById(R.id.category_image);
//        categoryTitle = itemView.findViewById(R.id.category_title);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        listener.onCategoryClick(categoryTitle.getText().toString());
    }
}

