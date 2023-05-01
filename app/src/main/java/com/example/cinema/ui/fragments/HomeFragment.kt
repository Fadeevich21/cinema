package com.example.cinema.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.databinding.FragmentHomeBinding
import com.example.cinema.ui.activies.TicketDetailActivity
import com.example.cinema.ui.adapters.TicketAdapter
import com.example.cinema.ui.decorations.MovieDecoration
import com.example.cinema.ui.viewModel.HomeViewModel

class HomeFragment : Fragment(), TicketAdapter.OnItemClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: TicketAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view: View = binding.root

        binding.ticketsSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filter(newText)
                return false
            }
        })

        binding.ticketsContainer.layoutManager = LinearLayoutManager(view.context)
        adapter = TicketAdapter(this)
        binding.ticketsContainer.adapter = adapter

        val decoration = MovieDecoration(top = 15, left = 20, right = 20)
        binding.ticketsContainer.addItemDecoration(decoration)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.ticketsLive.observe(requireActivity()) {
            adapter.submitList(it)
        }
        viewModel.getAllTickets()

        return view
    }

    override fun onTicketClick(id: Int) {
        val intent = Intent(requireActivity(), TicketDetailActivity::class.java)
        intent.putExtra("ticket_id", id)
        startActivity(intent)
    }
}