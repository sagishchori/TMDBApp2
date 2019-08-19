package com.sagishchori.tmdb_app.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sagishchori.tmdb_app.R;
import com.sagishchori.tmdb_app.activities.MainActivity;
import com.sagishchori.tmdb_app.databinding.MovieViewLayoutBinding;
import com.sagishchori.tmdb_app.logics.MovieDetailsFragmentLogic;
import com.sagishchori.tmdb_app.utils.ImageUtils;
import com.sagishchori.tmdb_app.viewmodels.LatestMoviesDataViewModel;
import com.squareup.picasso.Picasso;

public class MovieDetailsFragment extends BaseFragment {

    public static final String TAG = MovieDetailsFragment.class.getCanonicalName();

    private MovieViewLayoutBinding binding;
    private LatestMoviesDataViewModel viewModel;
    private MovieDetailsFragmentLogic logic;


    public static MovieDetailsFragment newInstance(Bundle args) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        logic = new MovieDetailsFragmentLogic(context, (MainActivity) context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        getViewModel();

        logic.setViewModel(viewModel);
    }

    @Override
    protected void getViewModel() {
        this.viewModel = ViewModelProviders.of(getActivity()).get(LatestMoviesDataViewModel.class);
    }

    @Override
    protected void loadData() {
        // Can be empty if no data need to be loaded.
    }

    @Override
    protected void setupActionBar() {
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_view_layout, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String url = logic.getMoviePoster(ImageUtils.PosterImageSize.PosterImageSize6);
        Picasso.get().load(url).into(binding.movieImage, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                binding.progressBar.setVisibility(View.GONE);
                binding.movieDesc.setText(logic.getMovieDescription());
                binding.movieYear.setText(logic.getMovieYear());
                binding.movieRating.setText(logic.getMovieRating());
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(getActivity(), "An error occurred, please re-launch the app...", Toast.LENGTH_LONG).show();
            }
        });

        setupActionBar();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_movie_details_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_to_favorite:
                logic.addMovieToFavoriteMoviesList();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public interface MovieDetailsFragmentInterface {

    }
}
