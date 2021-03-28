package com.example.movie.network;

import com.example.movie.BuildConfig;
import com.example.movie.model.Movie;
import com.example.movie.model.MovieDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("popular?api_key="+ BuildConfig.API_KEY)
    Call<Movie> getMovies(@Query("page") int page);

    @GET("{movieId}?api_key="+BuildConfig.API_KEY+"&language=en-US&append_to_response=videos")
    Call<MovieDetails> getMovieDetails(@Path("movieId") int movieId);
}
