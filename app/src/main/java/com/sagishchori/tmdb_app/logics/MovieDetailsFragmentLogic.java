package com.sagishchori.tmdb_app.logics;

import android.content.Context;

import androidx.room.Room;

import com.sagishchori.tmdb_app.activities.MainActivity;
import com.sagishchori.tmdb_app.api.ApiClient;
import com.sagishchori.tmdb_app.database.AppDataBase;
import com.sagishchori.tmdb_app.database.DataBaseInsertAgent;
import com.sagishchori.tmdb_app.models.MovieData;
import com.sagishchori.tmdb_app.utils.ImageUtils;
import com.sagishchori.tmdb_app.viewmodels.LatestMoviesDataViewModel;

public class MovieDetailsFragmentLogic extends BaseLogic implements DataBaseInsertAgent.DbInterface {

    private MainFragmentLogic.MainFragmentInterface listener;
    private AppDataBase db;

    public MovieDetailsFragmentLogic(Context context, MainActivity mainActivity) {
        super(context);

         db = Room.databaseBuilder(context, AppDataBase.class, "database-name").build();
    }

    public String getMovieDescription() {
        return getViewModel().getMovieDescription();
    }

    public String getMovieYear() {
        return getViewModel().getMovieYear();
    }

    public String getMovieRating() {
        return getViewModel().getMovieRating();
    }

    @Override
    public LatestMoviesDataViewModel getViewModel() {
        return (LatestMoviesDataViewModel) this.viewModel;
    }

    public String getMoviePoster(ImageUtils.PosterImageSize posterImageSize) {
        return ApiClient.getInstance().getImageUrl(posterImageSize.getValue(), getViewModel().getSelectedMovieData().getPoster_path());
    }

    public void addMovieToFavoriteMoviesList() {
        MovieData data = getViewModel().getSelectedMovieData();


        DataBaseInsertAgent agent = new DataBaseInsertAgent(context, this);
        agent.execute(data);

//        db.fmDao().insertAllFavoriteMovies(data);
    }


    @Override
    public void onDataInsert() {

    }
}
