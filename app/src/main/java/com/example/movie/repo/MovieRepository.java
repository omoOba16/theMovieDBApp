package com.example.movie.repo;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.movie.model.MovieDetails;
import com.example.movie.network.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    public LiveData<MovieDetails> getMovieDetails(int movieId){
        final MutableLiveData<MovieDetails> movies = new MutableLiveData<>();
        ApiClient.getInstance().getApiService().getMovieDetails(movieId).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetails> call,@NonNull Response<MovieDetails> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        movies.setValue(response.body());
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<MovieDetails> call,@NonNull Throwable t) {
                movies.setValue(null);
            }
        });
        return movies;
    }
}
