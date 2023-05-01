package com.example.cinema.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.databinding.ItemTicketBinding
import com.example.cinema.domain.model.Ticket

class TicketAdapter(private val clickListener: OnItemClickListener) :
    ListAdapter<Ticket, TicketAdapter.TicketViewHolder>(TicketComparator()) {

    interface OnItemClickListener {
        fun onTicketClick(id: Int)
    }

    inner class TicketViewHolder(private val binding: ItemTicketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ticket: Ticket) = with(binding) {
            ticketName.text = ticket.movie.name
            root.setOnClickListener { clickListener.onTicketClick(ticket.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        return TicketViewHolder(
            ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TicketComparator : DiffUtil.ItemCallback<Ticket>() {
        override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
            return oldItem == newItem
        }
    }
}