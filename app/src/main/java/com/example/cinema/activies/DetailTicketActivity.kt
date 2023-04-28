package com.example.cinema.activies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.cinema.R
import com.example.cinema.database.DatabaseManager
import com.example.cinema.database.entities.UserTicketEntity
import kotlin.concurrent.thread

class DetailTicketActivity : AppCompatActivity() {
    var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_ticket)

        val nameView = findViewById<TextView>(R.id.ticket_detail_name)
        val descriptionView = findViewById<TextView>(R.id.ticket_detail_description)
        val durationView = findViewById<TextView>(R.id.ticket_detail_duration)
        val placeView = findViewById<TextView>(R.id.ticket_detail_place)

        id = intent.getIntExtra("movie_id", -1)
        thread {
            val databaseManager = DatabaseManager()
            val ticket = databaseManager.getTicketById(id)
            val userTicketEntity = UserTicketEntity {
                this.ticketId = ticket
                this.userId = 1
            }

            databaseManager.insertUserTicketEntity(userTicketEntity)
            Log.d("flog", "onCreate: $ticket")

            runOnUiThread {
                nameView.text = ticket.movie.name
                descriptionView.text = ticket.movie.description
                durationView.text = ticket.movie.duration
                placeView.text = "row: ${ticket.place.row}, seat: ${ticket.place.seat}"
            }
        }
    }
}