package com.example.movie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VidResult {

    @SerializedName("site")
    @Expose
    private String site;
    @SerializedName("key")
    @Expose
    private String key;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
