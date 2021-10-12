package com.winda.couchpotato.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.winda.couchpotato.R
import com.winda.couchpotato.data.Show
import com.winda.couchpotato.databinding.ItemShowsCardBinding

class RecyclerPagedShowAdapter(private val activity : Activity) :
    PagedListAdapter<Show, RecyclerPagedShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var binding : ItemShowsCardBinding

    // buat onItemClick
    private var onItemClickCallback : OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback : OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Show> = object : DiffUtil.ItemCallback<Show>() {
            override fun areItemsTheSame(oldShow : Show, newShow : Show): Boolean {
                return oldShow.id == newShow.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldShow : Show, newShow : Show): Boolean {
                return oldShow == newShow
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) as Show)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding = ItemShowsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: ItemShowsCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(show : Show){
            binding.tvTitle.text = show.getTitleWithReleaseYear()
            binding.tvOverview.text = show.overview

            // glide poster
            if (show.posterUrl != null){
                Glide.with(itemView.context)
                    .load(show.posterUrl)
                    .placeholder(R.drawable.app_icon_bg)
                    .error(R.drawable.app_icon_bg) // will be displayed if the image cannot be loaded
                    .dontAnimate()
                    .into(binding.imgPoster)
            }


            // glide backdrop
            if (show.backdropUrl != null){
                Glide.with(itemView.context)
                    .load(show.backdropUrl)
                    .placeholder(R.drawable.app_icon_bg)
                    .error(R.drawable.gradient_main) // will be displayed if the image cannot be loaded
                    .dontAnimate()
                    .into(binding.imgBackdrop)
            }


            // on click
            itemView.setOnClickListener {
                onItemClickCallback?.onItemClickCallback(show)
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClickCallback(show : Show)
    }
}