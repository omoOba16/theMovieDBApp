package com.example.movie.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movie.model.MovieDetails;
import com.example.movie.repo.MovieRepository;

public class MovieDetailsViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository();
    }

    public LiveData<MovieDetails> getMovieDetails(int movieId) {
        return movieRepository.getMovieDetails(movieId);
    }
}
