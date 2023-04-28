package com.example.cinema.purchased_ticket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.database.entities.TicketEntity

class PurchasedTicketAdapter(private var purchasedTickets: List<TicketEntity>) :
    RecyclerView.Adapter<PurchasedTicketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchasedTicketViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return PurchasedTicketViewHolder(
            layoutInflater.inflate(R.layout.purchased_ticket_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return purchasedTickets.size
    }

    override fun onBindViewHolder(holder: PurchasedTicketViewHolder, position: Int) {
        holder.nameView.text = purchasedTickets[position].movie.name
    }
}