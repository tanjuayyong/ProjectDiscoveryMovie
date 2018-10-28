package org.ourkidslearningjourney.discoverymovie;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class MovieLoadViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final MovieDatabase mMovieDB;
    private final int mMovieId;

    public MovieLoadViewModelFactory(MovieDatabase mMovieDB, int mMovieId) {
        this.mMovieDB = mMovieDB;
        this.mMovieId = mMovieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieLoadViewModel(mMovieDB, mMovieId);
    }

}
