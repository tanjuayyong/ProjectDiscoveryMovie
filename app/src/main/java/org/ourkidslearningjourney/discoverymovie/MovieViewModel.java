package org.ourkidslearningjourney.discoverymovie;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private static final String TAG = MovieViewModel.class.getSimpleName();
    private LiveData<List<MovieInfo>> movies;

    public MovieViewModel(@NonNull Application application) {
        super(application);

        MovieDatabase database = MovieDatabase.getInstance(this.getApplication());

        Log.d(TAG, "Actively retrieving movies from DB");
        movies = database.movieDAO().loadAllMovies();
        this.movies = movies;
    }

    public LiveData<List<MovieInfo>> getMovies() { return movies; }
}
