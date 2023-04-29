package com.example.cinema.ui.activies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.cinema.R
import com.example.cinema.data.db.AppDatabase
import kotlin.concurrent.thread

class MoviePosterDetailActivity : AppCompatActivity() {
    var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val nameView = findViewById<TextView>(R.id.movie_detail_name)
        val descriptionView = findViewById<TextView>(R.id.movie_detail_description)
        val durationView = findViewById<TextView>(R.id.movie_detail_duration)

        id = intent.getIntExtra("movie_id", -1)
        thread {
            val movie = AppDatabase.moviesDao.getMovieById(id)
            runOnUiThread {
                nameView.text = movie.name
                descriptionView.text = movie.description
                durationView.text = movie.duration
            }
        }
    }
}