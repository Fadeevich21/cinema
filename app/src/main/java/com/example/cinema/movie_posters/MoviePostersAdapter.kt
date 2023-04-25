package com.example.cinema.movie_posters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.database.entities.MovieEntity

class MoviePostersAdapter(
    private val onMoviePosterSelectedListener: OnMoviePosterSelectedListener,
    private var moviePosters: MutableList<MovieEntity>
) : RecyclerView.Adapter<MoviePostersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePostersViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return MoviePostersViewHolder(
            layoutInflater.inflate(R.layout.movie_poster_item, parent, false),
            onMoviePosterSelectedListener
        )
    }

    override fun getItemCount(): Int {
        return moviePosters.size
    }

    override fun onBindViewHolder(holder: MoviePostersViewHolder, position: Int) {
        holder.nameView.text = moviePosters[position].name
    }
}