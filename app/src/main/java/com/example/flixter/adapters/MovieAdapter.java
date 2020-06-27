package com.example.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.R;
import com.example.flixter.models.Movie;


import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

        List<Movie> movies;
        Context context;

        public MovieAdapter(Context context, List<Movie> movies) {
            this.context = context;
            this.movies = movies;
        }


        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
            return new ViewHolder(movieView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Movie movie = movies.get(position);
            holder.bind(movie);
        }

        // Returns the total number of items in the list
        @Override
        public int getItemCount() {
            return movies.size();
        }

        // Create the viewholder as a static inner class
        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tvTitle;
            TextView tvOverview;
            ImageView ivPoster;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                tvTitle = itemView.findViewById(R.id.tvTitle);
                tvOverview = itemView.findViewById(R.id.tvOverview);
                ivPoster = itemView.findViewById(R.id.ivPoster);
            }


            public void bind(Movie movie) {
                tvTitle.setText(movie.getTitle());
                tvOverview.setText(movie.getOverview());
                String imageUrl;

                if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    imageUrl = movie.getBackdropPath();
                }else {
                    imageUrl = movie.getPosterPath();
                }
                int radius = 50;
                int margin = 10;

                Glide.with(context).load(imageUrl).transform(new RoundedCornersTransformation(radius, margin)).into(ivPoster);
            }
        }
    }

