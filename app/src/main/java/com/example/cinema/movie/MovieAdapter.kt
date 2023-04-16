package com.example.cinema.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.OnMovieSelectedListener
import com.example.cinema.R

class MovieAdapter(private val onMovieSelectedListener: OnMovieSelectedListener) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.movie_item, parent, false), onMovieSelectedListener)
    }

    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
        return 100
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        TODO("Not yet implemented")
    }
}