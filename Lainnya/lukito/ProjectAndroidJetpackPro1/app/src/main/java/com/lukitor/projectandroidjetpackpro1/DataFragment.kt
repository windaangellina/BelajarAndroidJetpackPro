package com.lukitor.projectandroidjetpackpro1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DataFragment : Fragment() {
    private var tipe: String?= ""
    private lateinit var adapter: DataAdapter
    private lateinit var rvHeroes: RecyclerView
    private lateinit var viewModel: DataViewModel
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {tipe = it.getString("tipe")}
        viewModel = ViewModelProvider(requireActivity()).get(DataViewModel::class.java)
        adapter = DataAdapter(viewModel.getData(tipe.toString()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvHeroes = view.findViewById(R.id.rvFollowing)
        rvHeroes.setHasFixedSize(true)
        rvHeroes.layoutManager= LinearLayoutManager(context)
        rvHeroes.adapter=adapter
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {return inflater.inflate(R.layout.fragment_data, container, false)}

    companion object {
        @JvmStatic
        fun newInstance(tipe: String) = DataFragment().apply {arguments = Bundle().apply {putString("tipe", tipe)}}
    }
}