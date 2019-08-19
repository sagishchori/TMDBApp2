package com.sagishchori.tmdb_app.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.room.Room;

import com.sagishchori.tmdb_app.database.AppDataBase;
import com.sagishchori.tmdb_app.database.DataBaseFetchAgent;
import com.sagishchori.tmdb_app.fragments.MovieDetailsFragment;
import com.sagishchori.tmdb_app.logics.MainFragmentLogic;
import com.sagishchori.tmdb_app.models.MovieData;
import com.sagishchori.tmdb_app.viewmodels.LatestMoviesDataViewModel;
import com.sagishchori.tmdb_app.api.ApiClient;
import com.sagishchori.tmdb_app.fragments.MainFragment;
import com.sagishchori.tmdb_app.R;
import com.sagishchori.tmdb_app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainFragmentLogic.MainFragmentInterface, MovieDetailsFragment.MovieDetailsFragmentInterface {

    protected ActivityMainBinding activityMainBinding;
    protected Toolbar toolbar;
    protected LatestMoviesDataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(LatestMoviesDataViewModel.class);

        setupActionBar();

        ApiClient.getApiClient();

        setFragmentToView(setUpMainFragmentToView(), MainFragment.TAG);

        activityMainBinding.favoriteMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFavoriteMoviesActivity();
            }
        });
    }

    protected MainFragment setUpMainFragmentToView() {
        MainFragment fragment = MainFragment.newInstance(null);
        return fragment;
    }

    private void startFavoriteMoviesActivity() {
        Intent intent = new Intent(this, FavoriteMoviesActivity.class);
        startActivity(intent);
    }

    /**
     * A method to set a {@link Fragment} to container.
     *
     * @param fragment      The {@link Fragment} to set to container
     * @param tag           The TAG of the {@link Fragment}
     */
    private void setFragmentToView(Fragment fragment, String tag)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.addToBackStack(null).replace(R.id.container, fragment, tag).commit();
    }

    private void setupActionBar()
    {
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
            return;
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 1){
            getSupportFragmentManager().popBackStackImmediate();
            return;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onMovieItemClicked() {
        MovieDetailsFragment fragment = MovieDetailsFragment.newInstance(null);
        setFragmentToView(fragment, MovieDetailsFragment.TAG);
    }
}
