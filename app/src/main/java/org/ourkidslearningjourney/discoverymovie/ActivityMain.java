package org.ourkidslearningjourney.discoverymovie;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityMain extends AppCompatActivity implements AdapterMovie.MovieAdapterOnClickHandler {
    private static final String TAG = ActivityMain.class.getSimpleName();
    public static final String SELECTED_MOVIE_DETAIL = "MOVIE_DETAIL";

    private AdapterMovie mMovieAdapter;
    private RecyclerView mMovieList;
    private ProgressBar mLoadingIndicator;

    private MovieDatabase mMovieDB;
    private FetchMovieTask mLoadMovie;
    private ArrayList<MovieInfo> mMovieInfos;

    public static ArrayList<MovieInfo> mFavoriteMovieInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mMovieList = (RecyclerView) findViewById(R.id.rv_displaymovies);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mMovieList.setLayoutManager(layoutManager);

        if (getNetworkStatus(this)) {
            String urlToLoad = UtilsNetwork.MOVIEAPI_POPULARREQUEST + UtilsNetwork.MOVIEDB_APIKEY;

            mLoadMovie = new FetchMovieTask();
            mLoadMovie.execute(urlToLoad);
        } else {
            Toast.makeText(
                    this,
                    "No Network Connectivity!\nUnable to get Movie Information",
                    Toast.LENGTH_LONG).show();
        }

        mMovieDB = MovieDatabase.getInstance(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String urlToLoad = "";
        int idItemClicked = item.getItemId();

        if ((mLoadMovie.getStatus() != AsyncTask.Status.RUNNING) || (mLoadMovie.getStatus() != AsyncTask.Status.PENDING)) {
            switch (idItemClicked) {
                case R.id.action_sort_mostpopular:
                    urlToLoad = UtilsNetwork.MOVIEAPI_POPULARREQUEST + UtilsNetwork.MOVIEDB_APIKEY;
                    mLoadMovie = new FetchMovieTask();
                    mLoadMovie.execute(urlToLoad);
                    return true;
                case R.id.action_sort_toprated:
                    urlToLoad = UtilsNetwork.MOVIEAPI_TOPRATEDREQUEST + UtilsNetwork.MOVIEDB_APIKEY;
                    mLoadMovie = new FetchMovieTask();
                    mLoadMovie.execute(urlToLoad);
                    return true;
                case R.id.action_sort_favorite:
                    setupViewModel();
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(MovieInfo movieInfo) {
        Context context = ActivityMain.this;
        Class classDest = ActivityDetail.class;
        Intent intentToDest = new Intent(context, classDest);

        intentToDest.putExtra(SELECTED_MOVIE_DETAIL, movieInfo);
        startActivity(intentToDest);
    }

    public static boolean getNetworkStatus(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo currentNetworkInfo = connManager.getActiveNetworkInfo();

        boolean gotNetwork = currentNetworkInfo != null && currentNetworkInfo.isConnected();

        return gotNetwork;
    }


    private void setupViewModel() {
        MovieViewModel viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        viewModel.getMovies().observe(this, new Observer<List<MovieInfo>>() {
            @Override
            public void onChanged(@Nullable List<MovieInfo> movieInfos) {
                Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");

                mFavoriteMovieInfos = new ArrayList<MovieInfo>(movieInfos);
                if (mFavoriteMovieInfos.size() == 0) {
                    Toast.makeText(
                            getApplicationContext(),
                            "You have NOT added any FAVORITE yet",
                            Toast.LENGTH_LONG).show();
                } else {
                    mMovieList.setHasFixedSize(true);
                    mMovieAdapter = new AdapterMovie(
                            ActivityMain.this,
                            mFavoriteMovieInfos.size(),
                            mFavoriteMovieInfos);
                    mMovieList.setAdapter(mMovieAdapter);
                }
            }
        });
    }


    public class FetchMovieTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                mMovieInfos = UtilsJson.parseMovieJson(s);

                mMovieList.setHasFixedSize(true);
                mMovieAdapter = new AdapterMovie(
                        ActivityMain.this,
                        mMovieInfos.size(),
                        mMovieInfos);
                mMovieList.setAdapter(mMovieAdapter);

                mLoadingIndicator.setVisibility(View.INVISIBLE);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... param) {
            String movieRequest = param[0];

            URL movieURL = UtilsNetwork.buildURL(movieRequest);

            try {
                String jsonMovieResponse = UtilsNetwork.getResponseFromHttpUrl(movieURL);
                return jsonMovieResponse;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
