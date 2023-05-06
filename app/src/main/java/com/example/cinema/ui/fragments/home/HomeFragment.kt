package com.example.cinema.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.App
import com.example.cinema.databinding.FragmentHomeBinding
import com.example.cinema.ui.activies.addMovie.AddMovieActivity
import com.example.cinema.ui.activies.movieDetail.MovieDetailActivity
import com.example.cinema.ui.adapters.MovieAdapter
import com.example.cinema.ui.decorations.MovieDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), MovieAdapter.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<HomeViewModel>()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view: View = binding.root

        binding.moviesSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filter(newText)
                return false
            }
        })

        binding.movieAdd.setOnClickListener {
            val intent = Intent(requireActivity(), AddMovieActivity::class.java)
            startActivity(intent)
        }

        binding.moviesContainer.layoutManager = LinearLayoutManager(view.context)
        adapter = MovieAdapter(this)
        binding.moviesContainer.adapter = adapter

        val decoration = MovieDecoration(top = 15, left = 20, right = 20)
        binding.moviesContainer.addItemDecoration(decoration)

        viewModel.moviesLive.observe(requireActivity()) {
            adapter.submitList(it)
        }

        viewModel.canAddMovieLive.observe(requireActivity()) {
            if (viewModel.canAddMovieLive.value == true) {
                binding.movieAdd.visibility = View.VISIBLE
            } else {
                binding.movieAdd.visibility = View.GONE
            }
        }
        viewModel.checkUserCanAddMovie(user = App.user!!)

        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllMovies()
    }

    override fun onMoviePosterClick(id: Int) {
        val intent = Intent(requireActivity(), MovieDetailActivity::class.java)
        intent.putExtra("movie_id", id)
        startActivity(intent)
    }
}