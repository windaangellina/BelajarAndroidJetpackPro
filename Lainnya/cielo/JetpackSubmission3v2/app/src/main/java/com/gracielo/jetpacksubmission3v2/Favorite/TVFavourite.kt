package com.gracielo.jetpacksubmission3v2.Favourite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gracielo.jetpacksubmission3v2.Adapter.MovieAdapter
import com.gracielo.jetpacksubmission3v2.Adapter.TVAdapter
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.DetailActivity
import com.gracielo.jetpacksubmission3v2.R
import com.gracielo.jetpacksubmission3v2.TV.TVAdapters
import com.gracielo.jetpacksubmission3v2.TV.TVCallback
import com.gracielo.jetpacksubmission3v2.ViewModelFactory
import com.gracielo.jetpacksubmission3v2.databinding.FragmentMoviesFavouriteBinding
import com.gracielo.jetpacksubmission3v2.databinding.FragmentTvFavouriteBinding
import com.gracielo.jetpacksubmission3v2.vo.Status


class TVFavourite : Fragment(),TVCallback {

    private var _binding : FragmentTvFavouriteBinding? = null
    lateinit var adapter: TVAdapter
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentTvFavouriteBinding.inflate(inflater, container, false)
        return (binding?.root)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = obtainViewModel(this)
            binding!!.rvTvFav.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TVAdapters(this@TVFavourite)
            }
            viewModel.getFavTV().observe(viewLifecycleOwner,androidx.lifecycle.Observer { listTV ->
                if (listTV != null) {
                        binding!!.rvTvFav.adapter?.let { adapters ->
                            when (adapters) {
                                is TVAdapters -> {
                                    adapters.submitList(listTV)
                                    adapters.notifyDataSetChanged()
                                }
                            }
                        }
                }
            })
        }
    }


    private fun obtainViewModel(fragment: Fragment): TVFavourtieViewModel {
        val factory: ViewModelFactory? = ViewModelFactory.getInstance(requireActivity())
        return ViewModelProvider(fragment, factory!!).get(TVFavourtieViewModel::class.java)
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