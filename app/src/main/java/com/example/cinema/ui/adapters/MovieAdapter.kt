package com.example.cinema.ui.adapters

import com.example.cinema.domain.model.MoviePoster
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.ui.viewHolders.MovieViewHolder
import com.example.cinema.ui.listeners.OnMovieSelectedListener

class MovieAdapter(
    private val onMovieSelectedListener: OnMovieSelectedListener,
    private var movies: MutableList<MoviePoster>
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(
            layoutInflater.inflate(R.layout.item_ticket, parent, false),
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