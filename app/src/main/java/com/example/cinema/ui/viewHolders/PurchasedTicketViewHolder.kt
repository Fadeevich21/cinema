package com.example.cinema.ui.viewHolders

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R

class PurchasedTicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameView: TextView = itemView.findViewById(R.id.purchased_ticket_name)
    var returnButton: Button = itemView.findViewById(R.id.purchased_ticket_return);
}