package com.sagishchori.tmdb_app.logics;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import retrofit2.Response;


public abstract class BaseLogic<T extends ViewModel> {

    protected final Context context;
    protected T viewModel;
    private MainFragmentLogic.MainFragmentInterface listener;

    public BaseLogic(Context context) {
        this.context = context;
    }

    public void setViewModel(T viewModel) {
        this.viewModel = viewModel;
    }

    public abstract T getViewModel();
}
