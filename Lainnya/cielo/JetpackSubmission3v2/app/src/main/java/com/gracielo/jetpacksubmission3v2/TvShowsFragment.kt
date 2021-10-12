package com.gracielo.jetpacksubmission3v2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gracielo.jetpacksubmission3v2.Adapter.TVAdapter
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Film.Film
import com.gracielo.jetpacksubmission3v2.Movies.MovieAdapters
import com.gracielo.jetpacksubmission3v2.TV.TVAdapters
import com.gracielo.jetpacksubmission3v2.TV.TVCallback
import com.gracielo.jetpacksubmission3v2.TV.TVViewModel
import com.gracielo.jetpacksubmission3v2.databinding.FragmentTvShowsBinding
import com.gracielo.jetpacksubmission3v2.vo.Resource
import com.gracielo.jetpacksubmission3v2.vo.Status

class TvShowsFragment : Fragment(), TVCallback {

    private lateinit var binding: FragmentTvShowsBinding
    lateinit var adapter: TVAdapter
    private lateinit var listTV: ArrayList<Film>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = obtainViewModel(this)
           binding.rvTv.apply {
               layoutManager = LinearLayoutManager(context)
               adapter = TVAdapters(this@TvShowsFragment)
           }
            viewModel.TV.observe(viewLifecycleOwner, { listTV ->
                if (listTV != null) {
                    when (listTV.status) {
                        Status.SUCCESS -> {
                            binding.rvTv.adapter?.let { adapters ->
                                when (adapters) {
                                    is TVAdapters -> {
                                        adapters.submitList(listTV.data)
                                        adapters.notifyDataSetChanged()
                                    }
                                }
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    private fun obtainViewModel(fragment: Fragment): TVViewModel {
        val factory: ViewModelFactory? = ViewModelFactory.getInstance(requireActivity())
        return ViewModelProvider(fragment, factory!!).get(TVViewModel::class.java)
    }

    override fun onItemClicked(data: TVEntity) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.TYPE_FILM, "TV")
                .putExtra(DetailActivity.ID_FILM, data.id)
        )
    }
}