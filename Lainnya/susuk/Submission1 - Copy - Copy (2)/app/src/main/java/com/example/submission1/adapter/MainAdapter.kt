package com.example.submission1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1.R
import com.example.submission1.activitycode.DetailActivity
import com.example.submission1.codeclass.Film

class MainAdapter(val listFilm: ArrayList<Film>, val param: String, val context: Context) : RecyclerView.Adapter<MainAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_film, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val film = listFilm[position]
        Glide.with(holder.itemView.context)
            .load(film.avatar)
            .apply(RequestOptions().override(140, 100))
            .into(holder.imgPhoto)
        holder.tvName.text = film.judul
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("film", film)
            context.startActivity(intent)
        }
    }
}