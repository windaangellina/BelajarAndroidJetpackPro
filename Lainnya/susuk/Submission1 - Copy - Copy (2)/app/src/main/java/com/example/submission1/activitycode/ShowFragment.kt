package com.example.submission1.activitycode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.adapter.MainAdapter
import com.example.submission1.viewmodel.MainViewModel
import com.example.submission1.R
import com.example.submission1.codeclass.Film
import com.example.submission1.databinding.FragmentShowBinding
import com.example.submission1.db.RetrofitClient
import com.example.submission1.viewmodel.ViewModelFactory

class ShowFragment : Fragment() {
    private var _binding: FragmentShowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private var list: ArrayList<Film> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility = View.VISIBLE
        showRecyclerList()
        viewModel.getAllData("Marvel").observe(viewLifecycleOwner,{
            for (i in it.results){
                if(i.media_type.equals("tv")){
                    val avatar = RetrofitClient.BASE_URL_POSTER + i.poster_path
                    list.add(Film(i.name,i.overview,avatar))
                }
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
        val listAdapter = MainAdapter(list, "Show", requireContext())
        binding.listFollower.adapter = listAdapter
    }
}