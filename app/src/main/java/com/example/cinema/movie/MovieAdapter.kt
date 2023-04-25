package com.example.cinema.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.database.entities.MovieEntity

class MovieAdapter(
    private val onMovieSelectedListener: OnMovieSelectedListener,
    private var movies: MutableList<MovieEntity>
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(
            layoutInflater.inflate(R.layout.movie_item, parent, false),
            onMovieSelectedListener
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.nameView.text = movies[position].name
    }
}