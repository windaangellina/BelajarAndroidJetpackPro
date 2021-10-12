package com.example.submission1.activitycode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.adapter.MainAdapter
import com.example.submission1.viewmodel.MainViewModel
import com.example.submission1.codeclass.Film
import com.example.submission1.databinding.FragmentMovieBinding
import com.example.submission1.db.ResponseSearchMulti
import com.example.submission1.db.RetrofitClient
import com.example.submission1.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private var list: ArrayList<Film> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility = View.VISIBLE
        val liveData : LiveData<ResponseSearchMulti> = viewModel.getAllData("Avengers")
        showRecyclerList()
        viewModel.getAllData("Avengers").observe(viewLifecycleOwner,{
            for (i in it.results){
                if(i.media_type.equals("movie")){
                    val avatar = RetrofitClient.BASE_URL_POSTER + i.poster_path
                    list.add(Film(i.original_title,i.overview,avatar)) }
            }
            binding.listFollower.adapter?.notifyDataSetChanged()
        })
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private fun showRecyclerList() {
        binding.listFollower.layoutManager = LinearLayoutManager(activity)
        val listAdapter = MainAdapter(list, "Movie", requireContext())
        binding.listFollower.adapter = listAdapter
    }
}