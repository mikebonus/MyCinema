package com.sampler.mymovie_2020.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sampler.mymovie_2020.models.Result;

// ★★★★★ step #8) REST-API (This part I came up with my own solution!! 2020-06-24)
// Get-Request by inserting an 'ID' as param
// go to MovieSearchResponse.java for step #9
public class MovieResponse {

    // fields
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("runtime")
    @Expose
    private String runtime;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("homepage")
    @Expose
    private String homepage;

    @SerializedName("popularity")
    @Expose
    private String popularity;

    @SerializedName("release_date")
    @Expose
    private String release_date;

    @SerializedName("vote_count")
    @Expose
    private String vote_count;

    @SerializedName("poster_path")
    @Expose
    private String poster_path;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getOverview() {
        return overview;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getVote_count() {
        return vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", runtime='" + runtime + '\'' +
                ", overview='" + overview + '\'' +
                ", homepage='" + homepage + '\'' +
                ", popularity='" + popularity + '\'' +
                ", release_date='" + release_date + '\'' +
                ", vote_count='" + vote_count + '\'' +
                ", poster_path='" + poster_path + '\'' +
                '}';
    }
}
