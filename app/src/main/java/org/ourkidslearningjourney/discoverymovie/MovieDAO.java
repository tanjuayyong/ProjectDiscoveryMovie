package org.ourkidslearningjourney.discoverymovie;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MovieDAO {
    @Query("SELECT * FROM favmovie")
    LiveData<List<MovieInfo>> loadAllMovies();

    @Insert
    void insertMovie(MovieInfo movieInfo);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(MovieInfo movieInfo);

    @Delete
    void deleteMovie(MovieInfo movieInfo);

    @Query("SELECT * FROM favmovie WHERE movie_id = :movieid")
    LiveData<MovieInfo> loadMovieByMovieId(int movieid);
}
