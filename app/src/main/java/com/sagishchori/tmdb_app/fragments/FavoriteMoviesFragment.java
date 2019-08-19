package com.sagishchori.tmdb_app.fragments;

import android.os.Bundle;
import android.view.View;

import com.sagishchori.tmdb_app.database.DataBaseFetchAgent;
import com.sagishchori.tmdb_app.models.MovieData;

import java.util.ArrayList;
import java.util.Arrays;

public class FavoriteMoviesFragment extends MainFragment implements DataBaseFetchAgent.DbInterface {

    public static FavoriteMoviesFragment newInstance(Bundle args) {

        FavoriteMoviesFragment fragment = new FavoriteMoviesFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void loadData() {
        DataBaseFetchAgent agent = new DataBaseFetchAgent(getActivity(), this);
        agent.execute();
    }

    @Override
    public void onDataFetch(MovieData[] movies) {
        adapter.updateData(new ArrayList<MovieData>(Arrays.asList(movies)));
        mainBinding.progressBar.setVisibility(View.GONE);
        mainBinding.recyclerView.setVisibility(View.VISIBLE);
    }
}
