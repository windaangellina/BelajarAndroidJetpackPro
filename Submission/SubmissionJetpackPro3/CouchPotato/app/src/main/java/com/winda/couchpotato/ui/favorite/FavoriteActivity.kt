package com.winda.couchpotato.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.winda.couchpotato.R
import com.winda.couchpotato.adapter.RecyclerPagedShowAdapter
import com.winda.couchpotato.data.Show
import com.winda.couchpotato.data.viewmodel.ShowsViewModel
import com.winda.couchpotato.data.viewmodel.ViewModelFactory
import com.winda.couchpotato.databinding.ActivityFavoriteBinding
import com.winda.couchpotato.ui.detail.DetailActivity

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    // data
    private lateinit var showsViewModel : ShowsViewModel
    private lateinit var pagedShowsAdapter : RecyclerPagedShowAdapter
    private var listShow = ArrayList<Show>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showsViewModel = getViewModel()

        // display list of favorite shows
        initRecyclerList()
        observeFavorite()
    }

    private fun getViewModel() : ShowsViewModel {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this@FavoriteActivity)
        return ViewModelProvider(this, factory)[ShowsViewModel::class.java]
    }

    private fun observeFavorite() {
        showsViewModel.getListFavorite().observe(this, {
            listShow.clear()
            listShow.addAll(it)

            if (listShow.isEmpty()){
                setImageStatusVisibility(true)
            }
            else{
                setImageStatusVisibility(false)
            }
            pagedShowsAdapter.submitList(it)
        })
    }

    private fun initRecyclerList(){
        // set recycler
        binding.recyclerListShow.setHasFixedSize(true)
        binding.recyclerListShow.layoutManager = LinearLayoutManager(applicationContext)

        // adapter
        initRecyclerViewAdapter()
    }

    private fun initRecyclerViewAdapter(){
        // adapter
        pagedShowsAdapter = RecyclerPagedShowAdapter(this@FavoriteActivity)
        binding.recyclerListShow.adapter = pagedShowsAdapter

        // on click
        pagedShowsAdapter.setOnItemClickCallback(object : RecyclerPagedShowAdapter.OnItemClickCallback {
            override fun onItemClickCallback(show: Show) {
                val detailIntent = Intent(applicationContext, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.ARG_SHOW, show)
                startActivity(detailIntent)
            }
        })
    }

    private fun setImageStatusVisibility(showImage: Boolean){
        if (!showImage){
            binding.imgSearch.visibility = View.GONE
        }
        else{
            binding.imgSearch.visibility = View.VISIBLE
            binding.imgSearch.setImageResource(R.drawable.ic_undraw_empty_xct9)
        }
    }

}