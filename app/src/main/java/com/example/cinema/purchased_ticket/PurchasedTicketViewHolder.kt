package com.example.cinema.purchased_ticket

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R

class PurchasedTicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameView: TextView = itemView.findViewById(R.id.purchased_ticket_name)
}