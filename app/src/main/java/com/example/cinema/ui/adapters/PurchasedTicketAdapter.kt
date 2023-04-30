package com.example.cinema.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.data.db.AppDatabase
import com.example.cinema.domain.model.Ticket
import com.example.cinema.ui.activies.MainActivity
import com.example.cinema.ui.viewHolders.PurchasedTicketViewHolder
import kotlin.concurrent.thread

class PurchasedTicketAdapter(private var purchasedTickets: MutableList<Ticket>) :
    RecyclerView.Adapter<PurchasedTicketViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchasedTicketViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        context = parent.context

        return PurchasedTicketViewHolder(
            layoutInflater.inflate(R.layout.item_purchased_ticket, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return purchasedTickets.size
    }

    override fun onBindViewHolder(holder: PurchasedTicketViewHolder, position: Int) {
        holder.nameView.text = purchasedTickets[position].movie.name
        holder.returnButton.setOnClickListener {
            thread {
                AppDatabase.usersTicketsDao.removeUserTicketById(purchasedTickets[position].id)
                (context as MainActivity).runOnUiThread {
                    purchasedTickets.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, purchasedTickets.size - position)
                    Toast.makeText(context, "You have returned the ticket", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}