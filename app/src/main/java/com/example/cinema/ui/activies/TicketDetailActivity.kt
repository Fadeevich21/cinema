package com.example.cinema.ui.activies

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.cinema.R
import com.example.cinema.data.db.AppDatabase
import com.example.cinema.data.db.entities.TicketEntity
import com.example.cinema.data.db.entities.UserTicketEntity
import kotlin.concurrent.thread

class TicketDetailActivity : AppCompatActivity() {
    var id: Int = -1
    private lateinit var ticket: TicketEntity

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_ticket)

        val nameView = findViewById<TextView>(R.id.ticket_detail_name)
        val descriptionView = findViewById<TextView>(R.id.ticket_detail_description)
        val durationView = findViewById<TextView>(R.id.ticket_detail_duration)
        val placeView = findViewById<TextView>(R.id.ticket_detail_place)
        val ticketBuyButton = findViewById<Button>(R.id.ticket_detail_buy)

        id = intent.getIntExtra("movie_id", -1)
        thread {
            ticket = AppDatabase.ticketsDao.getTicketById(id)

            runOnUiThread {
                nameView.text = ticket.movie.name
                descriptionView.text = ticket.movie.description
                durationView.text = ticket.movie.duration
                placeView.text = "row: ${ticket.place.row}, seat: ${ticket.place.seat}"
            }
        }

        ticketBuyButton.setOnClickListener {
            thread {
                val userTicketEntity = UserTicketEntity {
                    this.ticketId = ticket
                    this.userId = 1
                }

                var isTicketBuy = false
                if (!AppDatabase.usersTicketsDao.checkTicketExist(ticket)) {
                    isTicketBuy = true
                    AppDatabase.usersTicketsDao.addUserTicketEntity(userTicketEntity)
                }

                runOnUiThread {
                    if (isTicketBuy)
                        Toast.makeText(this, "You bought a ticket", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(this, "Sorry, this ticket is sold out", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}