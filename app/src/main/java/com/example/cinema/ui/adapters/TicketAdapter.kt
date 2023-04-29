package com.example.cinema.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.data.db.entities.TicketEntity
import com.example.cinema.ui.viewHolders.TicketViewHolder
import com.example.cinema.ui.listeners.OnTicketSelectedListener

class TicketAdapter(
    private val onTicketSelectedListener: OnTicketSelectedListener,
    private var tickets: MutableList<TicketEntity>
) : RecyclerView.Adapter<TicketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return TicketViewHolder(
            layoutInflater.inflate(R.layout.item_ticket, parent, false),
            onTicketSelectedListener
        )
    }

    override fun getItemCount(): Int {
        return tickets.size
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.nameView.text = tickets[position].movie.name
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredTickets: MutableList<TicketEntity>) {
        tickets = filteredTickets
        notifyDataSetChanged()
    }

    fun getTickets(): MutableList<TicketEntity> {
        return tickets
    }
}