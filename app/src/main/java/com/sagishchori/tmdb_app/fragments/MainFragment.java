package com.sagishchori.tmdb_app.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sagishchori.tmdb_app.R;
import com.sagishchori.tmdb_app.activities.MainActivity;
import com.sagishchori.tmdb_app.adapters.ListItemsAdapter;
import com.sagishchori.tmdb_app.api.ApiClient;
import com.sagishchori.tmdb_app.api.responses.GetLatestMoviesResponse;
import com.sagishchori.tmdb_app.databinding.ContentMainBinding;
import com.sagishchori.tmdb_app.logics.MainFragmentLogic;
import com.sagishchori.tmdb_app.viewmodels.LatestMoviesDataViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends BaseFragment {

    public static final String TAG = MainFragment.class.getCanonicalName();

    protected ContentMainBinding mainBinding;
    protected ListItemsAdapter adapter;
    private LatestMoviesDataViewModel viewModel;
    private MainFragmentLogic logic;

    public static MainFragment newInstance(Bundle args) {
        MainFragment fragment = new MainFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        logic = new MainFragmentLogic(context, (MainActivity) context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getViewModel();

        logic.setViewModel(viewModel);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainBinding = DataBindingUtil.inflate(inflater, R.layout.content_main, container, false);
        return mainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupAdapter();

        // This is the case when first instantiating this fragment
        if (viewModel == null || viewModel.getResults() == null) {
            loadData();
        }

        // In case this fragment is rebuilt and there is a data in the viewModel no need to load
        // the data again.
        else {
            mainBinding.progressBar.setVisibility(View.GONE);
            mainBinding.recyclerView.setVisibility(View.VISIBLE);
        }

        setupActionBar();
    }

    @Override
    protected void loadData() {
        ApiClient.getInstance().nowPlaying(new Callback<GetLatestMoviesResponse>() {

            @Override
            public void onResponse(Call<GetLatestMoviesResponse> call, Response<GetLatestMoviesResponse> response) {
                logic.onResponse(response);
                adapter.updateData(viewModel.getResults());
                mainBinding.progressBar.setVisibility(View.GONE);
                mainBinding.recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<GetLatestMoviesResponse> call, Throwable t) {
                Log.e("Call error", t.getMessage());
                mainBinding.progressBar.setVisibility(View.GONE);

                Toast.makeText(getActivity(), "An error occurred, please re-launch the app...", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void setupActionBar() {
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void setupAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mainBinding.recyclerView.setLayoutManager(manager);
        if (adapter == null) {
            adapter = new ListItemsAdapter(null, logic);
        }
        mainBinding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void getViewModel() {
        this.viewModel = ViewModelProviders.of(getActivity()).get(LatestMoviesDataViewModel.class);
    }
}
