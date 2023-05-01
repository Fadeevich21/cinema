package com.example.cinema.ui.activies

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cinema.databinding.ActivityDetailTicketBinding
import com.example.cinema.ui.viewModel.TicketDetailViewModel

class TicketDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTicketBinding
    private lateinit var viewModel: TicketDetailViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TicketDetailViewModel::class.java]
        viewModel.ticketLive.observe(this) {
            binding.ticketDetailName.text = it.movie.name
            binding.ticketDetailDescription.text = it.movie.description
            binding.ticketDetailDuration.text = it.movie.duration
            binding.ticketDetailPlace.text = "row: ${it.place.row}, seat: ${it.place.seat}"
        }
        viewModel.uiState.value.ticketId = intent.getIntExtra("ticket_id", -1)
        viewModel.getTicketById(viewModel.uiState.value.ticketId)

        binding.ticketDetailBuy.setOnClickListener {
            viewModel.buyTicket(1, viewModel.uiState.value.ticketId)
            if (viewModel.uiState.value.isTicketBuy)
                Toast.makeText(this, "You bought a ticket", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Sorry, this ticket is sold out", Toast.LENGTH_SHORT).show()
        }
    }
}