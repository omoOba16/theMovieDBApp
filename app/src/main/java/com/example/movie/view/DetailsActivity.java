package com.example.movie.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.movie.R;
import com.example.movie.adapter.CustomBindingAdapter;
import com.example.movie.databinding.ActivityDetailsBinding;
import com.example.movie.model.Genre;
import com.example.movie.model.VidResult;
import com.example.movie.util.Constants;
import com.example.movie.viewmodel.MovieDetailsViewModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    public int movieId = 0;
    public String movieBDrop = "";
    public String moviePoster  = "";
    private MovieDetailsViewModel movieDetailsVM;
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        movieDetailsVM = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        getLifecycle().addObserver(binding.youtubePlayerView);

        setUpActionBar();
        getIntentData();
        setUpMovieObserver();
    }

    private void setUpActionBar(){
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getIntentData(){
        movieId = getIntent().getIntExtra(Constants.MOVIE_ID, 0);
        movieBDrop = getIntent().getStringExtra(Constants.MOVIE_BACK_DROP);
        moviePoster = getIntent().getStringExtra(Constants.MOVIE_POSTER);
        CustomBindingAdapter.loadBackDrop(binding.backgroundPoster, movieBDrop, moviePoster);
    }

    private void setUpMovieObserver() {
        movieDetailsVM.getMovieDetails(movieId).observe(this, movieDetails -> {
            if(movieDetails != null){
                binding.setData(movieDetails);
                if(!movieDetails.getVideos().getResults().isEmpty()){
                    setUpVideoPlayer(movieDetails.getVideos().getResults().get(0));
                }
                if(!movieDetails.getGenres().isEmpty()){
                    setUpGenre(movieDetails.getGenres(), movieDetails.getRuntime());
                }
            }
        });
    }

    private void setUpGenre(List<Genre> genres, Integer runtime) {

        TextView textView = new TextView(this);
        textView.setTextSize(14F);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        textView.append(runtime + "mins ");
        for(int i = 0; i < genres.size(); i++){
            textView.append(genres.get(i).getName());
            if(i != genres.size() - 1){
                textView.append(", ");
            }
        }
        binding.genre.addView(textView);
    }

    private void setUpVideoPlayer(VidResult result) {
        binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                if(result.getSite().equals("YouTube")){
                    youTubePlayer.loadVideo(result.getKey(), 0f);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return (true);
        }
        return(super.onOptionsItemSelected(item));
    }
}