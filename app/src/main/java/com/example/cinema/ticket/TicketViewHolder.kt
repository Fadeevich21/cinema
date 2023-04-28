package com.example.cinema.ticket

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R

class TicketViewHolder(itemView: View, onTicketSelectedListener: OnTicketSelectedListener) :
    RecyclerView.ViewHolder(itemView) {
    var nameView: TextView = itemView.findViewById(R.id.movie_name)

    init {
        setOnClickListener(itemView, onTicketSelectedListener)
    }

    private fun setOnClickListener(
        itemView: View,
        onTicketSelectedListener: OnTicketSelectedListener
    ) {
        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION)
                onTicketSelectedListener.onMovieSelected(adapterPosition)
        }
    }
}