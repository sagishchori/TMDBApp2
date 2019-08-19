package com.sagishchori.tmdb_app.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.sagishchori.tmdb_app.models.MovieData;

public class DataBaseFetchAgent extends AsyncTask<Void, Void, MovieData[]> {

    private final Context context;
    private AppDataBase db;
    private DbInterface dbInterface;

    public DataBaseFetchAgent(Context context, DbInterface dbInterface) {
        this.context = context;
        this.dbInterface = dbInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

         db = Room.databaseBuilder(context, AppDataBase.class, "database-name").build();
    }

    @Override
    protected MovieData[] doInBackground(Void... voids) {
        MovieData[] movies = db.fmDao().loadAllFavoriteMovies();
        return movies;
    }

    @Override
    protected void onPostExecute(MovieData[] movies) {
        super.onPostExecute(movies);

        if (dbInterface != null) {
            dbInterface.onDataFetch(movies);
        }
    }

    public interface DbInterface {

        void onDataFetch(MovieData[] movies);
    }
}
