package com.example.cinema.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.domain.model.MoviePoster
import com.example.cinema.ui.viewHolders.MoviePostersViewHolder
import com.example.cinema.ui.listeners.OnMoviePosterSelectedListener

class MoviePostersAdapter(
    private val onMoviePosterSelectedListener: OnMoviePosterSelectedListener,
    private var moviePosters: MutableList<MoviePoster>
) : RecyclerView.Adapter<MoviePostersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePostersViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return MoviePostersViewHolder(
            layoutInflater.inflate(R.layout.item_movie_poster, parent, false),
            onMoviePosterSelectedListener
        )
    }

    override fun getItemCount(): Int {
        return moviePosters.size
    }

    override fun onBindViewHolder(holder: MoviePostersViewHolder, position: Int) {
        holder.nameView.text = moviePosters[position].name
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredMoviePosters: MutableList<MoviePoster>) {
        moviePosters = filteredMoviePosters
        notifyDataSetChanged()
    }

    fun getMoviePosters(): MutableList<MoviePoster> {
        return moviePosters
    }
}