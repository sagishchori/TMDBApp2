package com.sagishchori.tmdb_app.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.sagishchori.tmdb_app.models.MovieData;

@Database(entities = {MovieData.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract DaoInterface fmDao();
}
