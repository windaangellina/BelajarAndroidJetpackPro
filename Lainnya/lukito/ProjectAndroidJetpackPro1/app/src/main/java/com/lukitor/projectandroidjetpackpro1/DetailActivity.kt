package com.lukitor.projectandroidjetpackpro1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.lukitor.projectandroidjetpackpro1.`class`.Movie
import com.lukitor.projectandroidjetpackpro1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this).get(DetailVieModel::class.java)
        if (intent.hasExtra("data")){
            val datamovie: Movie = viewModel.getData(intent.getStringExtra("data").toString())
            Glide.with(this).load(datamovie.image).into(binding.imgCover)
            Glide.with(this).load(datamovie.image).into(binding.imgMovie)
            binding.txtDJudul.text = datamovie.judul
            binding.txtRating.text = datamovie.rating
            binding.txtGenre.text = datamovie.genre
            binding.txtdeskripsi.text = datamovie.description
            binding.imgback.setOnClickListener { view -> finish() }
        }
    }
}