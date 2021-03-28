package com.example.movie.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.movie.model.Result;
import com.example.movie.model.data.MovieDataSourceFactory;
import com.example.movie.util.Constants;

public class MovieViewModel extends ViewModel {

    LiveData<PagedList<Result>> moviePagedList;
    LiveData<PageKeyedDataSource<Integer, Result>> movieDataSource;

    public MovieViewModel() {
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory();
        movieDataSource = movieDataSourceFactory.getMovieLiveDataSource();
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(Constants.PAGE_SIZE).build();

        moviePagedList = (new LivePagedListBuilder(movieDataSourceFactory, pagedListConfig))
                .build();
    }

    public LiveData<PagedList<Result>> getMoviePagedList(){
        return moviePagedList;
    }

}
