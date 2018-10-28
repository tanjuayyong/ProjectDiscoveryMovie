package org.ourkidslearningjourney.discoverymovie;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

public class MovieLoadViewModel extends ViewModel {
    private LiveData<MovieInfo> movie;

    public MovieLoadViewModel(MovieDatabase database, int movieid) {
        movie = database.movieDAO().loadMovieByMovieId(movieid);
    }

    public LiveData<MovieInfo> getMovie() { return movie; }
}
