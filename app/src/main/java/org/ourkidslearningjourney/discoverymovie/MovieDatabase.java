package org.ourkidslearningjourney.discoverymovie;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

@Database(entities = {MovieInfo.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    private static final String LOG_TAG = MovieDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "moviedb";
    private static MovieDatabase sInstance;

    public static MovieDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new movie database instance");

                sInstance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        MovieDatabase.class,
                        MovieDatabase.DATABASE_NAME).build();
            }
        }
        Log.d(LOG_TAG, "Getting movie database instance");
        return sInstance;
    }

    public abstract MovieDAO movieDAO();
}
