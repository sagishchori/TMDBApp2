package com.sagishchori.tmdb_app.logics;

import android.content.Context;

import com.sagishchori.tmdb_app.adapters.ListItemsAdapter;
import com.sagishchori.tmdb_app.api.responses.GetLatestMoviesResponse;
import com.sagishchori.tmdb_app.models.MovieData;
import com.sagishchori.tmdb_app.viewmodels.LatestMoviesDataViewModel;

import retrofit2.Response;

public class MainFragmentLogic extends BaseLogic implements ListItemsAdapter.OnListItemClickListener {

    private MainFragmentInterface listener;

    public MainFragmentLogic(Context context, MainFragmentInterface listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    public void onItemClicked(MovieData data) {
        getViewModel().initMovieDetailsData(data);

        if (listener != null) {
            listener.onMovieItemClicked();
        }
    }

    public void onResponse(Response<GetLatestMoviesResponse> response) {
        getViewModel().initLatestMoviesData(response.body());
    }

    @Override
    public LatestMoviesDataViewModel getViewModel() {
        return (LatestMoviesDataViewModel) this.viewModel;
    }

    public interface MainFragmentInterface {

        /**
         * A method to indicate a movie item of the {@link com.sagishchori.tmdb_app.fragments.MainFragment}
         * {@link android.support.v7.widget.RecyclerView} was clicked.
         */
        void onMovieItemClicked();
    }
}
