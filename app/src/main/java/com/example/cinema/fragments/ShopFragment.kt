package com.example.cinema.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinema.R
import com.example.cinema.database.DatabaseManager
import kotlin.concurrent.thread

class ShopFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.fragment_shop, container, false)

        thread {
            val databaseManager = DatabaseManager()
            val tickets = databaseManager.getTicketsByUserId(1)
            Log.d("flog", "tickets count: ${tickets.size}")
        }

        return view
    }
}