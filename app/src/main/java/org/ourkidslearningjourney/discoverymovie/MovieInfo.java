package org.ourkidslearningjourney.discoverymovie;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@Entity(tableName = "favmovie")
public class MovieInfo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "movie_id")
    private int movieId;

    @ColumnInfo(name = "movie_title")
    private String movieTitle;

    @ColumnInfo(name = "movie_orgtitle")
    private String movieOrgTitle;

    @ColumnInfo(name = "movie_votecount")
    private int movieVoteCount;

    @ColumnInfo(name = "movie_voteavg")
    private double movieVoteAvg;

    @ColumnInfo(name = "movie_popularity")
    private double moviePopularity;

    @ColumnInfo(name = "movie_posterpath")
    private String moviePosterPath;

    @ColumnInfo(name = "movie_backdroppath")
    private String movieBackdropPath;

    @ColumnInfo(name = "movie_orglang")
    private String movieOrgLanguage;

    @ColumnInfo(name = "movie_isvideo")
    private int isMovieVideo;

    @ColumnInfo(name = "movie_isadult")
    private int isMovieAdult;

    @ColumnInfo(name = "movie_overview")
    private String movieOverview;

    @ColumnInfo(name = "movie_releasedate")
    private String movieReleaseDate;

    @Ignore
    public MovieInfo(
            int movieId,
            String movieTitle,
            String movieOrgTitle,
            int movieVoteCount,
            double movieVoteAvg,
            double moviePopularity,
            String moviePosterPath,
            String movieBackdropPath,
            String movieOrgLanguage,
            int isMovieVideo,
            int isMovieAdult,
            String movieOverview,
            String movieReleaseDate) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieOrgTitle = movieOrgTitle;
        this.movieVoteCount = movieVoteCount;
        this.movieVoteAvg = movieVoteAvg;
        this.moviePopularity = moviePopularity;
        this.moviePosterPath = moviePosterPath;
        this.movieBackdropPath = movieBackdropPath;
        this.movieOrgLanguage = movieOrgLanguage;
        this.isMovieVideo = isMovieVideo;
        this.isMovieAdult = isMovieAdult;
        this.movieOverview = movieOverview;
        this.movieReleaseDate = movieReleaseDate;
    }

    public MovieInfo(
            int id,
            int movieId,
            String movieTitle,
            String movieOrgTitle,
            int movieVoteCount,
            double movieVoteAvg,
            double moviePopularity,
            String moviePosterPath,
            String movieBackdropPath,
            String movieOrgLanguage,
            int isMovieVideo,
            int isMovieAdult,
            String movieOverview,
            String movieReleaseDate) {
        this.id = id;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieOrgTitle = movieOrgTitle;
        this.movieVoteCount = movieVoteCount;
        this.movieVoteAvg = movieVoteAvg;
        this.moviePopularity = moviePopularity;
        this.moviePosterPath = moviePosterPath;
        this.movieBackdropPath = movieBackdropPath;
        this.movieOrgLanguage = movieOrgLanguage;
        this.isMovieVideo = isMovieVideo;
        this.isMovieAdult = isMovieAdult;
        this.movieOverview = movieOverview;
        this.movieReleaseDate = movieReleaseDate;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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

    public void setMoviePopularity(double moviePopularity) { this.moviePopularity = moviePopularity; }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public double getMovieVoteAvg() {
        return movieVoteAvg;
    }

    public void setMovieVoteAvg(double movieVoteAvg) { this.movieVoteAvg = movieVoteAvg; }

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) { this.moviePosterPath = moviePosterPath; }

    public String getMovieBackdropPath() {
        return movieBackdropPath;
    }

    public void setMovieBackdropPath(String movieBackdropPath) { this.movieBackdropPath = movieBackdropPath; }

    public String getMovieOrgLanguage() {
        return movieOrgLanguage;
    }

    public void setMovieOrgLanguage(String movieOrgLanguage) { this.movieOrgLanguage = movieOrgLanguage; }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) { this.movieReleaseDate = movieReleaseDate; }
}
