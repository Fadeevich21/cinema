package com.example.cinema.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.ui.activies.MoviePosterDetailActivity
import com.example.cinema.data.db.AppDatabase
import com.example.cinema.data.db.entities.MovieEntity
import com.example.cinema.ui.adapters.MoviePostersAdapter
import com.example.cinema.ui.decorations.MoviePostersDecoration
import com.example.cinema.ui.listeners.OnMoviePosterSelectedListener
import java.util.Locale
import kotlin.concurrent.thread

class MoviePostersFragment : Fragment(), OnMoviePosterSelectedListener {
    private var moviePosters = mutableListOf<MovieEntity>()
    private lateinit var adapter: MoviePostersAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_posters, container, false)

        val searchView: SearchView? = view?.findViewById(R.id.movie_posters_search)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })


        val recyclerView: RecyclerView? = view?.findViewById(R.id.movie_posters_container)
        recyclerView?.layoutManager = LinearLayoutManager(view?.context)
        adapter = MoviePostersAdapter(this, moviePosters)
        recyclerView?.adapter = adapter

        val decoration = MoviePostersDecoration(top = 15, left = 20, right = 20)
        recyclerView?.addItemDecoration(decoration)

        thread {
            val movies = AppDatabase.moviesDao.getAllMovies()
            requireActivity().runOnUiThread {
                this.moviePosters.clear()
                this.moviePosters.addAll(movies)
                adapter.notifyDataSetChanged()
            }
        }

        return view
    }

    override fun onMovieSelected(position: Int) {
        val intent = Intent(requireActivity(), MoviePosterDetailActivity::class.java)
        intent.putExtra("movie_id", adapter.getMoviePosters()[position].id)

        startActivity(intent)
    }

    private fun filter(text: String) {
        if (moviePosters.isEmpty()) return
        val filteredMoviePosters = mutableListOf<MovieEntity>()
        for (moviePoster in moviePosters)
            if (moviePoster.name.lowercase().contains(text.lowercase(Locale.getDefault())))
                filteredMoviePosters.add(moviePoster)

        adapter.filterList(filteredMoviePosters)
        if (filteredMoviePosters.isEmpty()) Toast.makeText(
            activity,
            "No Data Found..",
            Toast.LENGTH_SHORT
        ).show()
    }
}