package com.example.cinema.ticket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.database.entities.MovieEntity
import com.example.cinema.database.entities.TicketEntity

class TicketAdapter(
    private val onTicketSelectedListener: OnTicketSelectedListener,
    private var movies: MutableList<TicketEntity>
) : RecyclerView.Adapter<TicketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return TicketViewHolder(
            layoutInflater.inflate(R.layout.movie_item, parent, false),
            onTicketSelectedListener
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.nameView.text = movies[position].movie.name
    }
}