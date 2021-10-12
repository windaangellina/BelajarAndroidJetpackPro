package com.example.submission1.activitycode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1.codeclass.Film
import com.example.submission1.viewmodel.MainViewModel
import com.example.submission1.R
import com.example.submission1.databinding.ActivityDetailBinding
import com.example.submission1.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var film: Film? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        film = intent.getParcelableExtra("film")
        Glide.with(this)
                .load(film?.avatar)
                .apply(RequestOptions().override(200, 150))
                .into(binding.imgItemPhoto)
        binding.tvJudul.text = film?.judul.toString()
        binding.tvIsi.text = film?.isi.toString()
    }
}