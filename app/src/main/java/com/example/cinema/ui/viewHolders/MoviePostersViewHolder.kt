package com.example.cinema.ui.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.ui.listeners.OnMoviePosterSelectedListener

class MoviePostersViewHolder(
    itemView: View,
    onMoviePosterSelectedListener: OnMoviePosterSelectedListener
) : RecyclerView.ViewHolder(itemView) {
    var nameView: TextView = itemView.findViewById(R.id.movie_poster_name)
    var imageView: ImageView = itemView.findViewById(R.id.movie_poster_image)

    init {
        setOnClickListener(itemView, onMoviePosterSelectedListener)
    }

    private fun setOnClickListener(
        itemView: View,
        onMoviePosterSelectedListener: OnMoviePosterSelectedListener
    ) {
        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION)
                onMoviePosterSelectedListener.onMovieSelected(adapterPosition)
        }
    }
}