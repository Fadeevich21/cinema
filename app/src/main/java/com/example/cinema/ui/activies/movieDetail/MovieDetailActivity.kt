package com.example.cinema.ui.activies.movieDetail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.App
import com.example.cinema.databinding.ActivityDetailMovieBinding
import com.example.cinema.domain.model.Movie
import com.example.cinema.ui.adapters.GenreAdapter
import com.example.cinema.ui.decorations.GenreDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private val viewModel by viewModel<MovieDetailViewModel>()

    private lateinit var adapter: GenreAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.movieDetailGenres.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = GenreAdapter()
        binding.movieDetailGenres.adapter = adapter

        val decoration = GenreDecoration(left = 10, right = 0)
        binding.movieDetailGenres.addItemDecoration(decoration)

        viewModel.movieLive.observe(this) { movie ->
            // TODO: это костыль! Заменить
            viewModel.checkBoughtMovieByUser(user = App.user!!, movie = viewModel.movieLive.value as Movie)

            binding.movieDetailName.text = movie.name
            binding.movieDetailDescription.text = movie.description
            binding.movieDetailDuration.text = movie.duration
            binding.movieDetailYearOfRelease.text = movie.yearOfRelease.toString()
            binding.movieDetailAgeRestriction.text = "${movie.ageRestriction}+"
            binding.movieDetailTrailer.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(movie.trailerUrl)
                startActivity(intent)
            }
            binding.movieDetailBuy.setOnClickListener {
                viewModel.buyMovie(user = App.user!!, movie = viewModel.movieLive.value as Movie)
            }
            binding.movieDetailShow.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(movie.contentUrl)
                startActivity(intent)
            }
            binding.movieDelete.setOnClickListener {
                viewModel.deleteMovie(viewModel.movieLive.value as Movie)
                finish()
            }
        }

        viewModel.genresLive.observe(this) { genres ->
            adapter.submitList(genres)
        }

        viewModel.isBoughtMovieLive.observe(this) {
            if (viewModel.isBoughtMovieLive.value == true) {
                binding.movieDetailBuy.isEnabled = false
                binding.movieDetailBuy.text = "Куплено"
                binding.movieDetailShow.visibility = View.VISIBLE
            }
        }

        viewModel.canDeleteMovie.observe(this) {
            if (viewModel.canDeleteMovie.value == true)
                binding.movieDelete.visibility = View.VISIBLE
            else
                binding.movieDelete.visibility = View.GONE
        }
        viewModel.checkUserCanDeleteMovie(user = App.user!!)

        viewModel.uiState.value.movieId = intent.getIntExtra("movie_id", -1)
        viewModel.getMovieById(viewModel.uiState.value.movieId)
        viewModel.getGenresByMovieId(viewModel.uiState.value.movieId)
    }
}