package com.sagishchori.tmdb_app.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.sagishchori.tmdb_app.models.MovieData;

import java.util.ArrayList;
import java.util.List;

public class DataBaseInsertAgent extends AsyncTask<MovieData, Void, Void> {

    private final Context context;
    private AppDataBase db;
    private DbInterface dbInterface;

    public DataBaseInsertAgent(Context context, DbInterface dbInterface) {
        this.context = context;
        this.dbInterface = dbInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        db = Room.databaseBuilder(context, AppDataBase.class, "database-name").build();
    }

    @Override
    protected Void doInBackground(MovieData... movieData) {

        MovieData[] movieData1 = db.fmDao().loadAllFavoriteMovies();

        List<MovieData> movies = new ArrayList<>();
        for (MovieData data:
             movieData1) {
            movies.add(data);
        }

        movies.add(movieData[0]);

        db.fmDao().insertAllFavoriteMovies(movies);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if (dbInterface != null) {
            dbInterface.onDataInsert();
        }
    }

    public interface DbInterface {

        void onDataInsert();
    }
}
