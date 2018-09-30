package org.ourkidslearningjourney.discoverymovie;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class MovieInfo implements Serializable {
    private int movieId;
    private String movieTitle;
    private String movieOrgTitle;
    private int movieVoteCount;
    private double movieVoteAvg;
    private double moviePopularity;
    private String moviePosterPath;
    private String movieBackdropPath;
    private String movieOrgLanguage;
    private int isMovieVideo;
    private int isMovieAdult;
    private String movieOverview;
    private String movieReleaseDate;

    public MovieInfo() {

    }

    public MovieInfo(
            int movieid,
            String movietitle,
            String movieorgtitle,
            int movievotecount,
            double movievoteavg,
            double moviepopularity,
            String movieposter,
            String moviebackdrop,
            String movielang,
            int isvideo,
            int isadult,
            String movieoverview,
            String moviereleasedate) {
        this.movieId = movieid;
        this.movieTitle = movietitle;
        this.movieOrgTitle = movieorgtitle;
        this.movieVoteCount = movievotecount;
        this.movieVoteAvg = movievoteavg;
        this.moviePopularity = moviepopularity;
        this.moviePosterPath = movieposter;
        this.movieBackdropPath = moviebackdrop;
        this.movieOrgLanguage = movielang;
        this.isMovieVideo = isvideo;
        this.isMovieAdult = isadult;
        this.movieOverview = movieoverview;
        this.movieReleaseDate = moviereleasedate;
    }



    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getIsMovieVideo() {
        return isMovieVideo;
    }

    public void setIsMovieVideo(int isMovieVideo) {
        this.isMovieVideo = isMovieVideo;
    }

    public int getIsMovieAdult() {
        return isMovieAdult;
    }

    public void setIsMovieAdult(int isMovieAdult) {
        this.isMovieAdult = isMovieAdult;
    }

    public String getMovieOrgTitle() {
        return movieOrgTitle;
    }

    public void setMovieOrgTitle(String movieOrgTitle) {
        this.movieOrgTitle = movieOrgTitle;
    }

    public int getMovieVoteCount() {
        return movieVoteCount;
    }

    public void setMovieVoteCount(int movieVoteCount) {
        this.movieVoteCount = movieVoteCount;
    }

    public double getMoviePopularity() {
        return moviePopularity;
    }

    public void setMoviePopularity(double moviePopularity) {
        this.moviePopularity = moviePopularity;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public double getMovieVoteAvg() {
        return movieVoteAvg;
    }

    public void setMovieVoteAvg(int movieVoteAvg) {
        this.movieVoteAvg = movieVoteAvg;
    }

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        this.moviePosterPath = moviePosterPath;
    }

    public String getMovieBackdropPath() {
        return movieBackdropPath;
    }

    public void setMovieBackdropPath(String movieBackdropPath) {
        this.movieBackdropPath = movieBackdropPath;
    }

    public String getMovieOrgLanguage() {
        return movieOrgLanguage;
    }

    public void setMovieOrgLanguage(String movieOrgLanguage) {
        this.movieOrgLanguage = movieOrgLanguage;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }
}
