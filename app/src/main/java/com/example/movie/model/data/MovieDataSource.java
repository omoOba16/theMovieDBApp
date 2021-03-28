package com.example.movie.model.data;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.movie.model.Movie;
import com.example.movie.model.Result;
import com.example.movie.network.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, Result> {

    int pageNum = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Result> callback) {

        ApiClient.getInstance().getApiService().getMovies(pageNum).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        callback.onResult(response.body().getResults(), null, 2);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<Movie> call,@NonNull Throwable t) {
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
        int page = params.key;
        ApiClient.getInstance().getApiService().getMovies(page).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call,@NonNull Response<Movie> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        callback.onResult(response.body().getResults(), page - 1);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<Movie> call,@NonNull Throwable t) {
            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
        int page = params.key;
        ApiClient.getInstance().getApiService().getMovies(page).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call,@NonNull Response<Movie> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        callback.onResult(response.body().getResults(), page + 1);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<Movie> call,@NonNull Throwable t) {
            }
        });
    }
}
