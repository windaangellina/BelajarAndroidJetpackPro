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
import com.gracielo.jetpacksubmission3v2.Adapter.FilmAdapter
import com.gracielo.jetpacksubmission3v2.Adapter.MovieAdapter
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.FilmEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.DetailActivity
import com.gracielo.jetpacksubmission3v2.Movies.MovieAdapters
import com.gracielo.jetpacksubmission3v2.Movies.MovieCallback
import com.gracielo.jetpacksubmission3v2.Movies.MoviesViewModel
import com.gracielo.jetpacksubmission3v2.R
import com.gracielo.jetpacksubmission3v2.ViewModelFactory
import com.gracielo.jetpacksubmission3v2.databinding.FragmentMoviesBinding
import com.gracielo.jetpacksubmission3v2.databinding.FragmentMoviesFavouriteBinding
import com.gracielo.jetpacksubmission3v2.vo.Resource
import com.gracielo.jetpacksubmission3v2.vo.Status


class MoviesFavourite : Fragment(),MovieCallback {

    private var _binding : FragmentMoviesFavouriteBinding? = null
    lateinit var adapter: MovieAdapter
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMoviesFavouriteBinding.inflate(inflater, container, false)
        return (binding?.root)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = obtainViewModel(this)
            binding!!.rvMoviesFav.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = MovieAdapters(this@MoviesFavourite)
            }
            viewModel.getFavMovies().observe(viewLifecycleOwner, {
                if (it != null) {
                    binding!!.rvMoviesFav.adapter?.let { adapters ->
                        when (adapters) {
                            is MovieAdapters -> {
                                adapters.submitList(it)
                                adapters.notifyDataSetChanged()
                            }
                        }
                    }
                }
            })
        }
    }

    private fun obtainViewModel(fragment: Fragment): MoviesFavouriteViewModel {
        val factory: ViewModelFactory? = ViewModelFactory.getInstance(requireActivity())
        return ViewModelProvider(fragment, factory!!).get(MoviesFavouriteViewModel::class.java)
    }

    override fun onItemClicked(data: MovieEntity) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.TYPE_FILM, "Movies")
                .putExtra(DetailActivity.ID_FILM, data.id)
        )
    }
}