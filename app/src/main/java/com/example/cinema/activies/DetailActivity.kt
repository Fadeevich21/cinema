package com.example.cinema.activies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.cinema.R
import com.example.cinema.database.DatabaseManager
import com.example.cinema.database.entities.UserTicketEntity
import kotlin.concurrent.thread

class DetailActivity : AppCompatActivity() {
    var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val nameView = findViewById<TextView>(R.id.movie_detail_name)
        val descriptionView = findViewById<TextView>(R.id.movie_detail_description)
        val durationView = findViewById<TextView>(R.id.movie_detail_duration)

        id = intent.getIntExtra("movie_id", -1)
        thread {
            val databaseManager = DatabaseManager()
            val movie = databaseManager.getMovieById(id)
//            val userTicketEntity = UserTicketEntity {
//                this.ticketId = movie
//                this.userId = 1
//            }

//            databaseManager.insertUserTicketEntity(userTicketEntity)
//            Log.d("flog", "onCreate: $movie")

            runOnUiThread {
                nameView.text = movie.name
                descriptionView.text = movie.description
                durationView.text = movie.duration
            }
        }
    }
}