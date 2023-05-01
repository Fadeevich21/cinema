package com.example.cinema.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.databinding.ItemMoviePosterBinding
import com.example.cinema.domain.model.MoviePoster

class MoviePosterAdapter(private val clickListener: OnItemClickListener) :
    ListAdapter<MoviePoster, MoviePosterAdapter.MoviePosterViewHolder>(MoviePosterComparator()) {

    interface OnItemClickListener {
        fun onMoviePosterClick(id: Int)
    }

    inner class MoviePosterViewHolder(private val binding: ItemMoviePosterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(moviePoster: MoviePoster) = with(binding) {
            moviePosterName.text = moviePoster.name
            root.setOnClickListener { clickListener.onMoviePosterClick(moviePoster.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        return MoviePosterViewHolder(
            ItemMoviePosterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MoviePosterComparator : DiffUtil.ItemCallback<MoviePoster>() {
        override fun areItemsTheSame(oldItem: MoviePoster, newItem: MoviePoster): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MoviePoster, newItem: MoviePoster): Boolean {
            return oldItem == newItem
        }
    }
}