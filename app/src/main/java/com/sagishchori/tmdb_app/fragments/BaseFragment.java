package com.sagishchori.tmdb_app.fragments;

import android.support.v4.app.Fragment;

public abstract class BaseFragment<T extends Fragment> extends Fragment {

    /**
     * A method to get the {@link android.arch.lifecycle.ViewModel}.
     */
    protected abstract void getViewModel();

    /**
     * A method to load data from server (optional).
     */
    protected abstract void loadData();

    /**
     * A method to set the ActionBar. Each fragment can control the {@link android.app.ActionBar}
     * via this method.
     */
    protected abstract void setupActionBar();
}
