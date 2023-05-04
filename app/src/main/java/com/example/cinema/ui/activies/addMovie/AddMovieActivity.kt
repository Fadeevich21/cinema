package com.example.cinema.ui.activies.addMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cinema.databinding.ActivityAddMovieBinding
import com.example.cinema.domain.model.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMovieBinding
    private val viewModel by viewModel<AddMovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.isAddedMovieLive.observe(this) {
            if (viewModel.isAddedMovieLive.value == true) {
                Toast.makeText(this, "Movie is added", Toast.LENGTH_SHORT).show()
                finish()
            } else
                Toast.makeText(this, "Movie is not added", Toast.LENGTH_SHORT).show()
        }

        binding.movieAddButton.setOnClickListener {
            val name = binding.movieAddFieldName.text.toString()
            val description = binding.movieAddFieldDescription.text.toString()
            val duration = binding.movieAddFieldDuration.text.toString()

            val yearOfReleaseString = binding.movieAddFieldYearOfRelease.text.toString()
            var yearOfRelease = 0
            if (yearOfReleaseString.isNotEmpty())
                yearOfRelease = yearOfReleaseString.toInt()
            val ageRestrictionString = binding.movieAddFieldAgeRestriction.text.toString()

            var ageRestriction = 0
            if (ageRestrictionString.isNotEmpty())
                ageRestriction = ageRestrictionString.toInt()

            val trailerUrl = binding.movieAddFieldTrailerUrl.text.toString()
            val contentUrl = binding.movieAddFieldContentUrl.text.toString()

            val movie = Movie(
                name = name,
                description = description,
                duration = duration,
                yearOfRelease = yearOfRelease,
                ageRestriction = ageRestriction,
                trailerUrl = trailerUrl,
                contentUrl = contentUrl
            )
            viewModel.addMovie(movie = movie)
        }
    }
}