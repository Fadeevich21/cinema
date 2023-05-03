package com.example.cinema.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.databinding.ItemMovieBinding
import com.example.cinema.domain.model.Movie

class MovieAdapter(private val clickListener: OnItemClickListener) :
    ListAdapter<Movie, MovieAdapter.MoviePosterViewHolder>(MoviePosterComparator()) {

    interface OnItemClickListener {
        fun onMoviePosterClick(id: Int)
    }

    inner class MoviePosterViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) = with(binding) {
            movieName.text = movie.name
            root.setOnClickListener { clickListener.onMoviePosterClick(movie.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        return MoviePosterViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MoviePosterComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}