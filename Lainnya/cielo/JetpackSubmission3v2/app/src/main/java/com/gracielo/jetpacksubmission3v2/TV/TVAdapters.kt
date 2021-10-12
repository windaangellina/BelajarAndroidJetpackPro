package com.gracielo.jetpacksubmission3v2.TV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Movies.MovieAdapters
import com.gracielo.jetpacksubmission3v2.Movies.MovieCallback
import com.gracielo.jetpacksubmission3v2.databinding.ItemDesignBinding

class TVAdapters(private val callback: TVCallback) :
    PagedListAdapter<TVEntity, TVAdapters.ListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVEntity>() {
            override fun areItemsTheSame(oldItem: TVEntity, newItem: TVEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TVEntity, newItem: TVEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ListViewHolder(private val binding: ItemDesignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TVEntity) {
            with(binding) {
                Glide
                    .with(itemView.context)
                    .load("http://image.tmdb.org/t/p/w500//${data.photo}")
                    .apply(RequestOptions().override(140, 220))
                    .into(binding.gambarFilm)
                binding.txtJudulItem.text = data.judul
                binding.txtDescItem.text = data.genre
                binding.rbStar.rating=data.rating
                binding.txtYearItem.text = data.tahun
                itemView.setOnClickListener{
                    callback.onItemClicked(data)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVAdapters.ListViewHolder{
        val binding = ItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }
}