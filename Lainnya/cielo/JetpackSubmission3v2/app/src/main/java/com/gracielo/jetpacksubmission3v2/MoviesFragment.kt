package com.gracielo.jetpacksubmission3v2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.gracielo.jetpacksubmission3v2.Adapter.MovieAdapter
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.FilmEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Movies.MovieAdapters
import com.gracielo.jetpacksubmission3v2.Movies.MovieCallback
import com.gracielo.jetpacksubmission3v2.Movies.MoviesViewModel
import com.gracielo.jetpacksubmission3v2.databinding.FragmentMoviesBinding
import com.gracielo.jetpacksubmission3v2.vo.Resource
import com.gracielo.jetpacksubmission3v2.vo.Status
import java.util.*


class MoviesFragment : Fragment(),MovieCallback {
    private lateinit var binding: FragmentMoviesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            val viewModel = obtainViewModel(this)
            binding.rvMovies.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = MovieAdapters(this@MoviesFragment)
            }
            viewModel.getMovies().observe(viewLifecycleOwner, { listMovie ->
                if (listMovie != null) {
                    when (listMovie.status) {
                        Status.SUCCESS -> {
                            binding.rvMovies.adapter?.let { adapters ->
                                when (adapters) {
                                    is MovieAdapters -> {
                                        adapters.submitList(listMovie.data)
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

    private fun obtainViewModel(fragment: Fragment): MoviesViewModel {
        val factory: ViewModelFactory? = ViewModelFactory.getInstance(requireActivity())
        return ViewModelProvider(fragment, factory!!).get(MoviesViewModel::class.java)
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