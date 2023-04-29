package com.example.cinema.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.data.db.AppDatabase
import com.example.cinema.data.db.entities.TicketEntity
import com.example.cinema.ui.adapters.PurchasedTicketAdapter
import com.example.cinema.ui.decorations.PurchasedTicketDecoration
import kotlin.concurrent.thread

class ShopFragment : Fragment() {
    private var purchasedTickets = mutableListOf<TicketEntity>()
    private var recyclerView: RecyclerView? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.fragment_shop, container, false)

        recyclerView = view?.findViewById(R.id.purchased_tickets_container)
        recyclerView?.layoutManager = LinearLayoutManager(view?.context)

        val adapter = PurchasedTicketAdapter(purchasedTickets)
        recyclerView?.adapter = adapter

        val decoration = PurchasedTicketDecoration(top = 15, left = 20, right = 20)
        recyclerView?.addItemDecoration(decoration)

        thread {
            val tickets = AppDatabase.usersTicketsDao.getTicketsByUserId(1)

            requireActivity().runOnUiThread {
                this.purchasedTickets.clear()
                this.purchasedTickets.addAll(tickets.map { it.ticketId })
                adapter.notifyDataSetChanged()
            }
        }

        return view
    }
}