package com.gracielo.jetpacksubmission3v2.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.DetailActivity
import com.gracielo.jetpacksubmission3v2.R
import java.util.ArrayList

class TVAdapter (private val activity: Activity?) :
    RecyclerView.Adapter<TVAdapter.TVAdapterHolder>() {
    private val listFilm: MutableList<TVEntity> = ArrayList()

    companion object {
        const val JUDUL_FILM = "judul"
    }

    private fun getItems(): List<TVEntity?> {
        return listFilm
    }

    fun setItems(items: List<TVEntity>?) {
        if (items != null) {
            this.listFilm.clear()
            this.listFilm.addAll(items)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVAdapterHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_design, parent, false)
        val viewHolder = TVAdapterHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }

    override fun onBindViewHolder(holder: TVAdapterHolder, position: Int) {
        val film = listFilm.get(position)
        Glide.with(holder.itemView.context)
            .load("http://image.tmdb.org/t/p/w500//${film.photo}")
            .into(holder.imgPhoto)
//        holder.imgPhoto.setImageResource(film.photo)
        holder.txtJudul.text = film.judul
        holder.txtDesc.text = film.genre
        holder.rbar.rating = film.rating
        holder.txtYear.text = film.tahun
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.ID_FILM, film.id)
            intent.putExtra(DetailActivity.TYPE_FILM, film.kategori)
            holder.itemView.context.startActivity(intent)
        }
    }

    class TVAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.gambarFilm)
        var txtJudul: TextView = itemView.findViewById(R.id.txtJudulItem)
        var txtDesc: TextView = itemView.findViewById(R.id.txtDescItem)
        var txtYear: TextView = itemView.findViewById(R.id.txtYearItem)
        var rbar: RatingBar = itemView.findViewById(R.id.rb_star)
    }
}