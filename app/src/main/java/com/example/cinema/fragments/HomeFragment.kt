package com.example.cinema.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.OnMovieSelectedListener
import com.example.cinema.R
import com.example.cinema.movie.MovieAdapter
import com.example.cinema.movie.MovieDecoration

class HomeFragment : Fragment() {
    private lateinit var mListener: OnMovieSelectedListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView? = view?.findViewById(R.id.list)
        recyclerView?.layoutManager = LinearLayoutManager(view?.context)
        val adapter = MovieAdapter(requireActivity() as OnMovieSelectedListener)
        recyclerView?.adapter = adapter

        val decoration = MovieDecoration(top = 15, left = 20, right = 20)
        recyclerView?.addItemDecoration(decoration)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = requireActivity() as OnMovieSelectedListener
    }
}