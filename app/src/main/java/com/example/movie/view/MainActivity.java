package com.example.movie.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.movie.R;
import com.example.movie.adapter.MovieAdapter;
import com.example.movie.databinding.ActivityMainBinding;
import com.example.movie.model.Result;
import com.example.movie.util.Constants;
import com.example.movie.util.SpacingItemDecoration;
import com.example.movie.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieItemClickListener {

    private MovieViewModel movieVM;
    private ActivityMainBinding binding;
    private GridLayoutManager gridLayoutManager;
    private MovieAdapter movieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        movieVM = ViewModelProviders.of(this).get(MovieViewModel.class);

        setAdapter();
        setUpMovieObserver();
    }

    private void setAdapter(){
        movieAdapter = new MovieAdapter(this, this);
        gridLayoutManager = new GridLayoutManager(this, 3);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.addItemDecoration(new SpacingItemDecoration(10));
        binding.recyclerView.setAdapter(movieAdapter);
    }

    private void setUpMovieObserver(){
        movieVM.getMoviePagedList().observe(this, items -> movieAdapter.submitList(items));
    }

    @Override
    public void onMovieItemClickListener(Result movie) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(Constants.MOVIE_ID,movie.getId());
        intent.putExtra(Constants.MOVIE_BACK_DROP,movie.getBackdropPath());
        intent.putExtra(Constants.MOVIE_POSTER,movie.getPosterPath());
        startActivity(intent);
    }
}