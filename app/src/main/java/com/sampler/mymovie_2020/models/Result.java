package com.sampler.mymovie_2020.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.sampler.mymovie_2020.util.Constants;

// IMAGES: https://image.tmdb.org/t/p/w342/or06FN3Dka5tukK1e9sl16pB3iy.jpg
// POSTER-BASE-URL: https://image.tmdb.org/t/p/w342/

public class Result implements Parcelable {

    private static final String TAG = "Result";

    private int counter;

    private String poster_path;
    private double popularity;
    private int vote_count;
    private int id;
    private String title;
    private double vote_average;
    private String overview;
    private String release_date;

    public Result() {
    }

    public Result(int counter, String poster_path, double popularity, int vote_count, int id, String title, double vote_average, String overview, String release_date) {
        this.counter = counter;
        this.poster_path = poster_path;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.id = id;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
    }

    protected Result(Parcel in) {
        counter = in.readInt();
        poster_path = in.readString();
        popularity = in.readDouble();
        vote_count = in.readInt();
        id = in.readInt();
        title = in.readString();
        vote_average = in.readDouble();
        overview = in.readString();
        release_date = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public static String getTAG() {
        return TAG;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getPoster_path(){
        if (Constants.POSTER_BASE_URL + poster_path != null) {
            return Constants.POSTER_BASE_URL + poster_path;
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }
        return "https://image.tmdb.org/t/p/w342/or06FN3Dka5tukK1e9sl16pB3iy.jpg";
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "Resulter{" +
                "counter=" + counter +
                ", poster_path='" + poster_path + '\'' +
                ", popularity=" + popularity +
                ", vote_count=" + vote_count +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", vote_average=" + vote_average +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(counter);
        parcel.writeString(poster_path);
        parcel.writeDouble(popularity);
        parcel.writeInt(vote_count);
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeDouble(vote_average);
        parcel.writeString(overview);
        parcel.writeString(release_date);
    }
}
