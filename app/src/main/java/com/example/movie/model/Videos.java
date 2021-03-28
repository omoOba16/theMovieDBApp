package com.example.movie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Videos {

    @SerializedName("results")
    @Expose
    private List<VidResult> results = null;

    public List<VidResult> getResults() {
        return results;
    }

    public void setResults(List<VidResult> results) {
        this.results = results;
    }
}
