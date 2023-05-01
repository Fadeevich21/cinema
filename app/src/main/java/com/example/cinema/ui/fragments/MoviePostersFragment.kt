package com.example.cinema.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.databinding.FragmentMoviePostersBinding
import com.example.cinema.ui.activies.MoviePosterDetailActivity
import com.example.cinema.ui.adapters.MoviePosterAdapter
import com.example.cinema.ui.decorations.MoviePostersDecoration
import com.example.cinema.ui.viewModel.MoviePostersViewModel

class MoviePostersFragment : Fragment(), MoviePosterAdapter.OnItemClickListener {
    private var _binding: FragmentMoviePostersBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MoviePostersViewModel
    private lateinit var adapter: MoviePosterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviePostersBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.moviePostersSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filter(newText)
                return false
            }
        })

        binding.moviePostersContainer.layoutManager = LinearLayoutManager(view.context)
        adapter = MoviePosterAdapter(this)
        binding.moviePostersContainer.adapter = adapter

        val decoration = MoviePostersDecoration(top = 15, left = 20, right = 20)
        binding.moviePostersContainer.addItemDecoration(decoration)

        viewModel = ViewModelProvider(this)[MoviePostersViewModel::class.java]
        viewModel.moviePostersLive.observe(requireActivity()) {
            adapter.submitList(it)
        }
        viewModel.getAllMoviePosters()

        return view
    }

    override fun onMoviePosterClick(id: Int) {
        val intent = Intent(requireActivity(), MoviePosterDetailActivity::class.java)
        intent.putExtra("movie_id", id)
        startActivity(intent)
    }
}