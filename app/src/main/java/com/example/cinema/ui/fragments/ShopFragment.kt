package com.example.cinema.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.databinding.FragmentShopBinding
import com.example.cinema.ui.activies.TicketDetailActivity
import com.example.cinema.ui.adapters.TicketAdapter
import com.example.cinema.ui.decorations.TicketDecoration
import com.example.cinema.ui.viewModel.ShopViewModel

class ShopFragment : Fragment(), TicketAdapter.OnItemClickListener {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ShopViewModel
    private lateinit var adapter: TicketAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.purchasedTicketsContainer.layoutManager = LinearLayoutManager(view.context)
        adapter = TicketAdapter(this)
        binding.purchasedTicketsContainer.adapter = adapter

        val decoration = TicketDecoration(top = 15, left = 20, right = 20)
        binding.purchasedTicketsContainer.addItemDecoration(decoration)

        viewModel = ViewModelProvider(this)[ShopViewModel::class.java]
        viewModel.ticketsLive.observe(requireActivity()) {
            adapter.submitList(it)
        }
        viewModel.getTicketsByUserId(1)

        return view
    }

    override fun onTicketClick(id: Int) {
        val intent = Intent(requireActivity(), TicketDetailActivity::class.java)
        intent.putExtra("ticket_id", id)
        startActivity(intent)
    }
}