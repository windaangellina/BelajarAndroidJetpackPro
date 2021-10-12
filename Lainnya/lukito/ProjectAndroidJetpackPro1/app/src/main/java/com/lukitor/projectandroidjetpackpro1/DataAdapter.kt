package com.lukitor.projectandroidjetpackpro1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lukitor.projectandroidjetpackpro1.`class`.Movie


class DataAdapter(private var data: MutableList<Movie>) :RecyclerView.Adapter<DataAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_cardview,
            parent,
            false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position])
    }
    override fun getItemCount(): Int {return data.size}

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var gambar: ImageView = itemView.findViewById(R.id.gambarcard)
        var judul: TextView = itemView.findViewById(R.id.txtJudul)
        var tipe: TextView = itemView.findViewById(R.id.txtTipe)
        fun bind(movie: Movie){
            judul.text= movie.judul
            tipe.text= movie.type
            Glide.with(itemView.context).load(movie.image).into(gambar)
            itemView.setOnClickListener{
                val intent= Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("data",movie.judul)
                itemView.context.startActivity(intent)
            }
        }
    }
}