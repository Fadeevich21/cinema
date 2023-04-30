package com.example.cinema.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.data.repository.TicketRepositoryImpl
import com.example.cinema.domain.model.Ticket
import com.example.cinema.domain.usecase.GetAllTicketsUseCase
import com.example.cinema.ui.activies.TicketDetailActivity
import com.example.cinema.ui.adapters.TicketAdapter
import com.example.cinema.ui.decorations.MovieDecoration
import com.example.cinema.ui.listeners.OnTicketSelectedListener
import java.util.Locale
import kotlin.concurrent.thread

class HomeFragment : Fragment(), OnTicketSelectedListener {
    private var tickets = mutableListOf<Ticket>()
    private lateinit var adapter: TicketAdapter

    private val repository = TicketRepositoryImpl()
    private val getAllTicketsUseCase = GetAllTicketsUseCase(repository)

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.fragment_home, container, false)

        val searchView: SearchView? = view?.findViewById(R.id.tickets_search)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })

        val recyclerView: RecyclerView? = view?.findViewById(R.id.tickets_container)
        recyclerView?.layoutManager = LinearLayoutManager(view?.context)
        adapter = TicketAdapter(this, tickets)
        recyclerView?.adapter = adapter

        thread {
            val tickets = getAllTicketsUseCase.execute()
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
        val intent = Intent(requireActivity(), TicketDetailActivity::class.java)
        intent.putExtra("movie_id", adapter.getTickets()[position].id)

        startActivity(intent)
    }

    private fun filter(text: String) {
        if (tickets.isEmpty()) return
        val filteredTickets = mutableListOf<Ticket>()
        for (ticket in tickets)
            if (ticket.movie.name.lowercase().contains(text.lowercase(Locale.getDefault())))
                filteredTickets.add(ticket)

        adapter.filterList(filteredTickets)
        if (filteredTickets.isEmpty()) Toast.makeText(
            activity,
            "No Data Found..",
            Toast.LENGTH_SHORT
        ).show()
    }
}