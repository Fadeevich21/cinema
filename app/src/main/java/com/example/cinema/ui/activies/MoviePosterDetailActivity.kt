package com.example.cinema.ui.activies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.cinema.databinding.ActivityDetailMoviePosterBinding
import com.example.cinema.ui.viewModel.MoviePosterDetailViewModel

class MoviePosterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMoviePosterBinding
    private lateinit var viewModel: MoviePosterDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMoviePosterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MoviePosterDetailViewModel::class.java]
        viewModel.moviePosterLive.observe(this) {
            binding.movieDetailName.text = it.name
            binding.movieDetailDescription.text = it.description
            binding.movieDetailDuration.text = it.duration
        }
        viewModel.uiState.value.moviePosterId = intent.getIntExtra("movie_id", -1)
        viewModel.getMoviePosterById(viewModel.uiState.value.moviePosterId)
    }
}