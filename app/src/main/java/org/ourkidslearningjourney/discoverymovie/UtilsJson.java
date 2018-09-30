package org.ourkidslearningjourney.discoverymovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UtilsJson {

    public static ArrayList<MovieTrailer> parseTrailerJson(String json) throws JSONException {
        final String API_RESULT = "results";
        final String TRAILER_KEY = "key";
        final String TRAILER_NAME = "name";
        final String TRAILER_SITE = "site";
        final String TRAILER_TYPE = "type";
        final String TRAILER_SIZE = "size";

        JSONObject jsonTrailers = new JSONObject(json);

        JSONArray jsonTrailerResults = jsonTrailers.getJSONArray(API_RESULT);
        ArrayList<MovieTrailer> trailerInfos = new ArrayList<MovieTrailer>(jsonTrailerResults.length());

        for (int i=0; i<jsonTrailerResults.length(); i++) {
            JSONObject jsonReview = jsonTrailerResults.getJSONObject(i);

            String key = jsonReview.getString(TRAILER_KEY);
            String name = jsonReview.getString(TRAILER_NAME);
            String site = jsonReview.getString(TRAILER_SITE);
            String type = jsonReview.getString(TRAILER_TYPE);
            int size = jsonReview.getInt(TRAILER_SIZE);

            MovieTrailer review = new MovieTrailer(name, site, type, key, size);
            trailerInfos.add(review);
        }

        return trailerInfos;
    }

    public static ArrayList<MovieReview> parseReviewJson(String json) throws JSONException {
        final String API_RESULT = "results";
        final String REVIEW_AUTHOR = "author";
        final String REVIEW_CONTENT = "content";
        final String REVIEW_ID = "id";

        JSONObject jsonReviews = new JSONObject(json);

        JSONArray jsonReviewResults = jsonReviews.getJSONArray(API_RESULT);
        ArrayList<MovieReview> reviewInfos = new ArrayList<MovieReview>(jsonReviewResults.length());

        for (int i=0; i<jsonReviewResults.length(); i++) {
            JSONObject jsonReview = jsonReviewResults.getJSONObject(i);

            String author = jsonReview.getString(REVIEW_AUTHOR);
            String content = jsonReview.getString(REVIEW_CONTENT);
            String id = jsonReview.getString(REVIEW_ID);

            MovieReview review = new MovieReview(author, content, id);
            reviewInfos.add(review);
        }

        return reviewInfos;
    }

    public static ArrayList<MovieInfo> parseMovieJson(String json) throws JSONException {
        final String API_RESULT = "results";
        final String MV_VCOUNT = "vote_count";
        final String MV_ID = "id";
        final String MV_ISVIDEO = "video";
        final String MV_VOTEAVG = "vote_average";
        final String MV_TITLE = "title";
        final String MV_POPULARITY = "popularity";
        final String MV_POSTER = "poster_path";
        final String MV_ORGLANG = "original_language";
        final String MV_ORGTITLE = "original_title";
        final String MV_BACKDROP = "backdrop_path";
        final String MV_ISADULT = "adult";
        final String MV_OVERVIEW = "overview";
        final String MV_RELEASEDATE = "release_date";

        JSONObject jsonMovies = new JSONObject(json);

        JSONArray jsonApiResults = jsonMovies.getJSONArray(API_RESULT);
        ArrayList<MovieInfo> movieInfos = new ArrayList<MovieInfo>(jsonApiResults.length());

        for (int i=0; i<jsonApiResults.length(); i++) {
            JSONObject jsonMovie = jsonApiResults.getJSONObject(i);

            int voteCount = jsonMovie.getInt(MV_VCOUNT);
            int id = jsonMovie.getInt(MV_ID);
            int isVideo = jsonMovie.getBoolean(MV_ISVIDEO) ? 1 : 0;
            double voteAvg = jsonMovie.getDouble(MV_VOTEAVG);
            String title = jsonMovie.getString(MV_TITLE);
            double popularity = jsonMovie.getDouble(MV_POPULARITY);
            String posterPath = jsonMovie.getString(MV_POSTER);
            String orgLang = jsonMovie.getString(MV_ORGLANG);
            String orgtitle = jsonMovie.getString(MV_ORGTITLE);
            String backdropPath = jsonMovie.getString(MV_BACKDROP);
            int isAdult = jsonMovie.getBoolean(MV_ISADULT) ? 1 : 0;
            String overView = jsonMovie.getString(MV_OVERVIEW);
            String releaseDate = jsonMovie.getString(MV_RELEASEDATE);

            MovieInfo movie = new MovieInfo(
                    id,
                    title,
                    orgtitle,
                    voteCount,
                    voteAvg,
                    popularity,
                    posterPath,
                    backdropPath,
                    orgLang,
                    isVideo,
                    isAdult,
                    overView,
                    releaseDate);

            movieInfos.add(movie);
        }

        return movieInfos;
    }
}
