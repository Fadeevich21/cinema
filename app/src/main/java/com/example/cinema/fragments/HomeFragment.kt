package com.example.cinema.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.movie.OnMovieSelectedListener
import com.example.cinema.R
import com.example.cinema.activies.DetailActivity
import com.example.cinema.activies.DetailTicketActivity
import com.example.cinema.database.DatabaseManager
import com.example.cinema.database.entities.MovieEntity
import com.example.cinema.database.entities.TicketEntity
import com.example.cinema.movie.MovieAdapter
import com.example.cinema.movie.MovieDecoration
import com.example.cinema.ticket.OnTicketSelectedListener
import com.example.cinema.ticket.TicketAdapter
import kotlin.concurrent.thread

class HomeFragment : Fragment(), OnTicketSelectedListener {
    private var tickets = mutableListOf<TicketEntity>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView? = view?.findViewById(R.id.list)
        recyclerView?.layoutManager = LinearLayoutManager(view?.context)
        val adapter = TicketAdapter(this, tickets)
        recyclerView?.adapter = adapter

        thread {
            val databaseManager = DatabaseManager()
            val tickets = databaseManager.getAllTickets()
            requireActivity().runOnUiThread {
                this.tickets.clear()
                this.tickets.addAll(tickets)
                adapter.notifyDataSetChanged()
            }
        }

        val decoration = MovieDecoration(top = 15, left = 20, right = 20)
        recyclerView?.addItemDecoration(decoration)

        return view
    }

    override fun onMovieSelected(position: Int) {
        val intent = Intent(requireActivity(), DetailTicketActivity::class.java)
        intent.putExtra("movie_id", tickets[position].id)

        startActivity(intent)
    }
}