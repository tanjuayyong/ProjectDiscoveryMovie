package org.ourkidslearningjourney.discoverymovie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;

public class ActivityDetail extends AppCompatActivity implements AdapterTrailer.TrailerAdapterOnClickHandler {
    private TextView lblMovieTitle;
    private TextView lblMovieReleaseDate;
    private TextView lblMovieAdultRating;
    private TextView lblMovieOrgLang;
    private TextView lblMovieVoteAvg;
    private TextView lblMovieDescription;

    private RatingBar ratingbarRating;

    private ImageView imgMoviePoster;

    private Button btnMovieAddToFavorite;

    private AdapterReview mReviewAdapter;
    private AdapterTrailer mTrailerAdapter;
    private RecyclerView mReviewList;

    private RecyclerView mTrailerList;

    private FetchReviewTask mLoadReview;
    private FetchTrailerTask mLoadTrailer;

    MovieInfo mDetailedMovieInfo;

    private ArrayList<MovieReview> mReviewInfos;
    private ArrayList<MovieTrailer> mTrailerInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mReviewList = (RecyclerView) findViewById(R.id.rv_displayreviews);
        mTrailerList = (RecyclerView) findViewById(R.id.rv_displaytrailers);

        LinearLayoutManager layoutReviewManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutTrailerManager = new LinearLayoutManager(this);
        mReviewList.setLayoutManager(layoutReviewManager);
        mTrailerList.setLayoutManager(layoutTrailerManager);

        lblMovieTitle = (TextView) findViewById(R.id.lbl_detailed_movietitle);
        lblMovieReleaseDate = (TextView) findViewById(R.id.lbl_detailed_moviereleasedate);
        lblMovieAdultRating = (TextView) findViewById(R.id.lbl_detailed_isadult);
        lblMovieOrgLang = (TextView) findViewById(R.id.lbl_detailed_orglang);
        lblMovieVoteAvg = (TextView) findViewById(R.id.lbl_detailed_voteavg);
        lblMovieDescription = (TextView) findViewById(R.id.lbl_detailed_description);

        btnMovieAddToFavorite = (Button) findViewById(R.id.btn_detailed_addfavorite);
        btnMovieAddToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = ActivityMain.mFavoriteMovieInfos.size();

                if (size == 0) {
                    ActivityMain.mFavoriteMovieInfos.add(mDetailedMovieInfo);

                    Toast.makeText(
                            getApplicationContext(),
                            "Successfully ADDED to your FAVORITE Movie List",
                            Toast.LENGTH_LONG).show();
                } else {
                    for (int i=0; i<size; i++) {
                        if (mDetailedMovieInfo.getMovieId() != ActivityMain.mFavoriteMovieInfos.get(i).getMovieId()) {
                            if (i == (size - 1)) {
                                ActivityMain.mFavoriteMovieInfos.add(mDetailedMovieInfo);

                                Toast.makeText(
                                        getApplicationContext(),
                                        "Successfully ADDED to your FAVORITE Movie List",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        });

        ratingbarRating = (RatingBar) findViewById(R.id.ratingbar_detailed_rating);

        imgMoviePoster = (ImageView) findViewById(R.id.img_detailed_movieposter);

        Intent intentDetailedMovie = getIntent();

        if (intentDetailedMovie.hasExtra(ActivityMain.SELECTED_MOVIE_DETAIL)) {
            mDetailedMovieInfo = (MovieInfo) intentDetailedMovie.getSerializableExtra(ActivityMain.SELECTED_MOVIE_DETAIL);

            if (ActivityMain.getNetworkStatus(this)) {
                String urlToLoad =
                        UtilsNetwork.MOVIEAPI_START_REQUEST +
                        mDetailedMovieInfo.getMovieId() +
                        UtilsNetwork.MOVIEAPI_MID_REVIEWREQUEST +
                        UtilsNetwork.MOVIEDB_APIKEY +
                        UtilsNetwork.MOVIEAPI_END_REQUEST;

                mLoadReview = new FetchReviewTask();
                mLoadReview.execute(urlToLoad);
            } else {
                Toast.makeText(
                        this,
                        "No Network Connectivity!\nUnable to get Movie Information",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(MovieTrailer trailerInfo) {
        Intent startYoutubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailerInfo.getTrailerKey()));

        if (startYoutubeIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(startYoutubeIntent);
        }
    }

    public class FetchTrailerTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                mTrailerInfos = UtilsJson.parseTrailerJson(s);

                mTrailerList.setHasFixedSize(true);
                mTrailerAdapter = new AdapterTrailer(
                        ActivityDetail.this,
                        mTrailerInfos.size(),
                        mTrailerInfos);
                mTrailerList.setAdapter(mTrailerAdapter);

                lblMovieTitle.setText(mDetailedMovieInfo.getMovieTitle());
                lblMovieReleaseDate.setText("Release: " + mDetailedMovieInfo.getMovieReleaseDate().substring(0, 4));
                if (mDetailedMovieInfo.getIsMovieAdult() == 1) {
                    lblMovieAdultRating.setText("Adult Rating: Yes");
                } else {
                    lblMovieAdultRating.setText("Adult Rating: No");
                }
                lblMovieOrgLang.setText("Language: " + mDetailedMovieInfo.getMovieOrgLanguage().toUpperCase());
                lblMovieVoteAvg.setText("Movie Rating: " + String.valueOf(mDetailedMovieInfo.getMovieVoteAvg()) + " / 10");
                lblMovieDescription.setText(mDetailedMovieInfo.getMovieOverview());
                ratingbarRating.setRating((float) mDetailedMovieInfo.getMovieVoteAvg());

                Picasso.with(ActivityDetail.this).load(AdapterMovie.MOVIE_STARTURL + mDetailedMovieInfo.getMoviePosterPath()).into(imgMoviePoster);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... param) {
            String movieRequest = param[0];

            URL reviewURL = UtilsNetwork.buildURL(movieRequest);

            try {
                String jsonTrailerResponse = UtilsNetwork.getResponseFromHttpUrl(reviewURL);
                return jsonTrailerResponse;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public class FetchReviewTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                mReviewInfos = UtilsJson.parseReviewJson(s);

                mReviewList.setHasFixedSize(true);
                mReviewAdapter = new AdapterReview(
                        mReviewInfos.size(),
                        mReviewInfos);
                mReviewList.setAdapter(mReviewAdapter);

                String urlToLoad =
                        UtilsNetwork.MOVIEAPI_START_REQUEST +
                                mDetailedMovieInfo.getMovieId() +
                                UtilsNetwork.MOVIEAPI_MID_VIDEOREQUEST +
                                UtilsNetwork.MOVIEDB_APIKEY +
                                UtilsNetwork.MOVIEAPI_END_REQUEST;

                mLoadTrailer = new FetchTrailerTask();
                mLoadTrailer.execute(urlToLoad);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... param) {
            String movieRequest = param[0];

            URL reviewURL = UtilsNetwork.buildURL(movieRequest);

            try {
                String jsonReviewResponse = UtilsNetwork.getResponseFromHttpUrl(reviewURL);
                return jsonReviewResponse;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
