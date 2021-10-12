package com.winda.submissionjetpackpro1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.winda.submissionjetpackpro1.R
import com.winda.submissionjetpackpro1.data.Show
import com.winda.submissionjetpackpro1.databinding.ItemShowsBinding

class RecyclerShowAdapter : RecyclerView
.Adapter<RecyclerShowAdapter.ViewHolder>() {

    private lateinit var binding : ItemShowsBinding
    private var listShows = ArrayList<Show>()

    // buat onItemClick
    private var onItemClickCallback : OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback : OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setListShows(list : ArrayList<Show>){
        this.listShows = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerShowAdapter.ViewHolder {
        binding = ItemShowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerShowAdapter.ViewHolder, position: Int) {
        holder.bind(listShows[position])
    }

    override fun getItemCount(): Int {
        return listShows.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(binding: ItemShowsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(show : Show){
            setVisibility(show)

            binding.tvTitle.text = show.title
            binding.tvRatings.text = show.ratings
            binding.tvDuration.text = show.getDurationAsString()
            binding.tvUserScores.text = show.getUserScoresAsString()
            binding.tvReleaseDate.text = show.getReleaseDateAsString()

            // glide poster
            Glide.with(itemView.context)
                .load(show.posterId)
                .apply(RequestOptions().override(80, 80))
                .placeholder(R.drawable.app_icon_bg)
                .error(R.drawable.app_icon_bg) // will be displayed if the image cannot be loaded
                .dontAnimate()
                .into(binding.imgPoster)

            // on click
            itemView.setOnClickListener {
                onItemClickCallback?.onItemClickCallback(show)
            }
        }

        private fun setVisibility(show: Show){
            if (show.durationHour == 0 && show.durationMinute == 0){
                binding.tvDuration.visibility = View.INVISIBLE
                binding.iconDuration.visibility = View.INVISIBLE
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClickCallback(show : Show)
    }
}