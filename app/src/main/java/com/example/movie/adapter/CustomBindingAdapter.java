package com.example.movie.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.example.movie.util.Constants;

public class CustomBindingAdapter {

    @BindingAdapter("setPoster")
    public static void loadMoviePoster(ImageView view, String url){
        if(url != null){
            Glide.with(view.getContext())
                    .load(Constants.MOVIE_IMAGE_POSTER_SIZE_W500 + url)
                    .into(view);
        }
    }

    public static void loadBackDrop(ImageView view, String backDrop, String poster){
        if(backDrop != null){
            Glide.with(view.getContext())
                    .load(Constants.MOVIE_IMAGE_BACKDROP_SIZE + backDrop)
                    .into(view);
        } else {
            if(poster != null){
                Glide.with(view.getContext())
                        .load(Constants.MOVIE_IMAGE_POSTER_SIZE_ORIGINAL + poster)
                        .into(view);
            }
        }
    }

    public static String getMovieYear(String releaseYear){
        String year = "";
        if(releaseYear != null){
            year = releaseYear.split("-")[0];
        }
        return year;
    }
}
