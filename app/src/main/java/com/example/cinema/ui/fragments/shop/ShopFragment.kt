package com.example.cinema.ui.fragments.shop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.App
import com.example.cinema.databinding.FragmentShopBinding
import com.example.cinema.ui.activies.movieDetail.MovieDetailActivity
import com.example.cinema.ui.adapters.MovieAdapter
import com.example.cinema.ui.decorations.MovieDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShopFragment : Fragment(), MovieAdapter.OnItemClickListener {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<ShopViewModel>()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.purchasedMoviesContainer.layoutManager = LinearLayoutManager(view.context)
        adapter = MovieAdapter(this)
        binding.purchasedMoviesContainer.adapter = adapter

        val decoration = MovieDecoration(top = 15, left = 20, right = 20)
        binding.purchasedMoviesContainer.addItemDecoration(decoration)

        viewModel.moviesLive.observe(requireActivity()) {
            adapter.submitList(it)
        }
        viewModel.getBoughtMoviesByUser(App.user!!)

        return view
    }

    override fun onMoviePosterClick(id: Int) {
        val intent = Intent(requireActivity(), MovieDetailActivity::class.java)
        intent.putExtra("movie_id", id)
        startActivity(intent)
    }
}