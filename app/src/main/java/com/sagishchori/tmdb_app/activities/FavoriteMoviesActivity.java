package com.sagishchori.tmdb_app.activities;

import android.os.Bundle;
import android.view.View;

import com.sagishchori.tmdb_app.fragments.FavoriteMoviesFragment;
import com.sagishchori.tmdb_app.fragments.MainFragment;

public class FavoriteMoviesActivity extends MainActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding.favoriteMoviesButton.hide();
    }

    @Override
    protected MainFragment setUpMainFragmentToView() {
        FavoriteMoviesFragment fragment = FavoriteMoviesFragment.newInstance(null);
        return fragment;
    }
}
