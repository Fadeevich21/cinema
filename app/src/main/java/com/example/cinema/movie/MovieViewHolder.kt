package com.example.cinema.movie

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R

class MovieViewHolder(itemView: View, onMovieSelectedListener: OnMovieSelectedListener) :
    RecyclerView.ViewHolder(itemView) {
    var nameView: TextView = itemView.findViewById(R.id.movie_name)

    init {
        setOnClickListener(itemView, onMovieSelectedListener)
    }

    private fun setOnClickListener(
        itemView: View,
        onMovieSelectedListener: OnMovieSelectedListener
    ) {
        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION)
                onMovieSelectedListener.onMovieSelected(adapterPosition)
        }
    }
}