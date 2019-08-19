package com.sagishchori.tmdb_app.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sagishchori.tmdb_app.R;
import com.sagishchori.tmdb_app.utils.ImageUtils;
import com.sagishchori.tmdb_app.api.ApiClient;
import com.sagishchori.tmdb_app.databinding.ListItemViewHolderBinding;
import com.sagishchori.tmdb_app.models.MovieData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListItemsAdapter extends RecyclerView.Adapter<ListItemsAdapter.ListItemViewHolder> {

    ArrayList <MovieData> movieData;
    OnListItemClickListener listener;

    public ListItemsAdapter(ArrayList <MovieData> movieData, OnListItemClickListener listener) {
        this.movieData = movieData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListItemsAdapter.ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ListItemViewHolderBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_view_holder, viewGroup, false);
        return new ListItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemsAdapter.ListItemViewHolder viewHolder, int i)
    {
        final MovieData data = this.movieData.get(i);
        viewHolder.onBind(data);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClicked(data);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        if (movieData != null) {
            return movieData.size();
        }

        return 0;
    }

    /**
     * Use this method to update the {@link ListItemsAdapter} data.
     *
     * @param movieData     The {@link MovieData} to update
     */
    public void updateData(ArrayList <MovieData> movieData) {
        this.movieData = movieData;
        notifyDataSetChanged();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder
    {
        ListItemViewHolderBinding binding;

        public ListItemViewHolder(ListItemViewHolderBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void onBind(MovieData data) {
            binding.title.setText(data.getTitle());

            String imageUrl = ApiClient.getInstance().getImageUrl(ImageUtils.LogoImageSize.LogoImageSize3.getValue(), data.getPoster_path());
            Picasso.get().load(imageUrl).into(binding.image);
        }
    }

    /**
     * An interface for passing the data of the clicked item.
     */
    public interface OnListItemClickListener {

        /**
         * A method to indicate a click on a list item.
         *
         * @param data      The {@link MovieData} to pass
         */
        void onItemClicked(MovieData data);
    }
}
