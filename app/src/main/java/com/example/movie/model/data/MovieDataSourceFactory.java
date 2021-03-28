package com.example.movie.model.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.movie.model.Result;

public class MovieDataSourceFactory extends DataSource.Factory {

    private final MutableLiveData<PageKeyedDataSource<Integer, Result>> movieLiveDataSource = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource create() {
        MovieDataSource movieDataSource = new MovieDataSource();
        movieLiveDataSource.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Result>> getMovieLiveDataSource() {
        return movieLiveDataSource;
    }
}
