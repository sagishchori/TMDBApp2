package com.sagishchori.tmdb_app.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.sagishchori.tmdb_app.api.responses.GetLatestMoviesResponse;
import com.sagishchori.tmdb_app.models.MovieData;

import java.util.ArrayList;

public class LatestMoviesDataViewModel extends ViewModel {


    private Context context;
    public MutableLiveData<GetLatestMoviesResponse> latestMoviesData;
    public MutableLiveData<MovieData> selectedMoviesData;
    private GetLatestMoviesResponse latestMoviesResponse;
    private MovieData selectedMovieData;

    public void initLatestMoviesData(GetLatestMoviesResponse response) {
        this.latestMoviesResponse = response;
        if (latestMoviesData != null) {
            latestMoviesData.setValue(latestMoviesResponse);
        }
    }

    public void initMovieDetailsData(MovieData data) {
        this.selectedMovieData = data;
        if (selectedMoviesData != null) {
            selectedMoviesData.setValue(selectedMovieData);
        }
    }

    private LiveData<GetLatestMoviesResponse> getLatestMoviesData() {
        if (latestMoviesData == null) {
            latestMoviesData = new MutableLiveData<>();
            latestMoviesData.setValue(latestMoviesResponse);
        }

        return latestMoviesData;
    }

    private LiveData<MovieData> getMovieDetailsData() {
        if (selectedMoviesData == null) {
            selectedMoviesData = new MutableLiveData<>();
            selectedMoviesData.setValue(selectedMovieData);
        }

        return selectedMoviesData;
    }

    /**
     * A method to get all latest movies list.
     *
     * @return An {@link ArrayList} of all {@link MovieData}s
     */
    public ArrayList<MovieData> getResults() {
        try {
            return getLatestMoviesData().getValue().getResults();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * A method to set the selected item's data to {@link LatestMoviesDataViewModel}.
     *
     * @param data The {@link MovieData} of the selected item
     */
    public void setSelectedMovie(MovieData data) {
        this.selectedMovieData = data;
    }

    public MovieData getSelectedMovieData() {
        return selectedMovieData;
    }

    public String getMovieDescription() {
        return getMovieDetailsData().getValue().getOverview();
    }

    public String getMovieYear() {
        return getMovieDetailsData().getValue().getRelease_date();
    }

    public String getMovieRating() {
        return String.valueOf(getMovieDetailsData().getValue().getVote_average());
    }
}
