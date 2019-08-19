package com.sagishchori.tmdb_app.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sagishchori.tmdb_app.models.MovieData;

import java.util.List;

@Dao
public interface DaoInterface {

    @Query("SELECT * FROM favorite_movies")
    public MovieData[] loadAllFavoriteMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAllFavoriteMovies(List<MovieData> movies);

}
