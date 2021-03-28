package com.example.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.BR;
import com.example.movie.databinding.MovieItemBinding;
import com.example.movie.model.Result;

public class MovieAdapter extends PagedListAdapter<Result, MovieAdapter.MovieViewHolder> {

    Context context;
    MovieItemClickListener movieItemClickListener;

     public MovieAdapter(Context context, MovieItemClickListener movieItemClickListener) {
        super(diffCallBack);
        this.context = context;
        this.movieItemClickListener = movieItemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MovieItemBinding movieItemBinding = MovieItemBinding.inflate(layoutInflater, parent, false);
        return new MovieViewHolder(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(getItem(position), movieItemClickListener);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        final MovieItemBinding movieItemBinding;
        public MovieViewHolder(@NonNull MovieItemBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;
        }
        public void bind(Result movie, MovieItemClickListener movieItemClickListener){
            movieItemBinding.setVariable(BR.movie, movie);
            movieItemBinding.setVariable(BR.movieItemClick, movieItemClickListener);
            movieItemBinding.executePendingBindings();
        }
    }

    private static DiffUtil.ItemCallback<Result> diffCallBack =
        new DiffUtil.ItemCallback<Result>() {
           @Override
           public boolean areItemsTheSame(Result oldItem, Result newItem) {
               return oldItem.getId().equals(newItem.getId());
           }

           @Override
           public boolean areContentsTheSame(Result oldItem, Result newItem) {
               return oldItem.getTitle().equals(newItem.getTitle());
           }
    };

    public interface MovieItemClickListener {
        void onMovieItemClickListener(Result movie);
    }
}
