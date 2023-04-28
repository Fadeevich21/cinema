package com.example.cinema.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.activies.DetailActivity
import com.example.cinema.database.DatabaseManager
import com.example.cinema.database.entities.MovieEntity
import com.example.cinema.movie.MovieAdapter
import com.example.cinema.movie.OnMovieSelectedListener
import com.example.cinema.movie_posters.MoviePostersAdapter
import com.example.cinema.movie_posters.MoviePostersDecoration
import com.example.cinema.movie_posters.OnMoviePosterSelectedListener
import kotlin.concurrent.thread

class MoviePostersFragment : Fragment(), OnMoviePosterSelectedListener {
    private var moviePosters = mutableListOf<MovieEntity>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_posters, container, false)

        val recyclerView: RecyclerView? = view?.findViewById(R.id.movie_posters_container)
        recyclerView?.layoutManager = LinearLayoutManager(view?.context)
        val adapter = MoviePostersAdapter(this, moviePosters)
        recyclerView?.adapter = adapter

        val decoration = MoviePostersDecoration(top = 15, left = 20, right = 20)
        recyclerView?.addItemDecoration(decoration)

        thread {
            val databaseManager = DatabaseManager()
            val movies = databaseManager.getAllMovies()
            requireActivity().runOnUiThread {
                this.moviePosters.clear()
                this.moviePosters.addAll(movies)
                adapter.notifyDataSetChanged()
            }
        }

        return view
    }

    override fun onMovieSelected(position: Int) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("movie_id", moviePosters[position].id)

        startActivity(intent)
    }
}