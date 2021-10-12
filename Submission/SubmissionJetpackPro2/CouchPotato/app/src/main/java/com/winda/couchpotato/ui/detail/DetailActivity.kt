package com.winda.couchpotato.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.winda.couchpotato.R
import com.winda.couchpotato.data.Show
import com.winda.couchpotato.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private var show : Show? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(ARG_SHOW)){
            show = intent.getParcelableExtra(ARG_SHOW)
        }

        setComponentValue()
    }

    companion object{
        const val ARG_SHOW = "show"
    }

    private fun setComponentValue(){
        binding.tvDetailJudul.text = show?.getTitleWithReleaseYear()
        binding.tvDetailUserScores.text = show?.getUserScoresAsString()
        binding.tvReleaseDate.text = show?.getReleaseDateAsString()
        binding.tvOverview.text = show?.overview

        // glide poster
        if (show?.posterUrl != null){
            Glide.with(applicationContext)
                .load(show?.posterUrl)
                .placeholder(R.drawable.app_icon_bg)
                .error(R.drawable.app_icon_bg) // will be displayed if the image cannot be loaded
                .dontAnimate()
                .into(binding.imgPoster)
        }


        // glide backdrop
        if (show?.backdropUrl != null){
            Glide.with(applicationContext)
                .load(show?.backdropUrl)
                .placeholder(R.drawable.app_icon_bg)
                .error(R.drawable.gradient_main) // will be displayed if the image cannot be loaded
                .dontAnimate()
                .into(binding.imgBackdrop)
        }

        setEvent()
    }

    private fun setEvent(){
        binding.iconShare.setOnClickListener {
             shareShow()
        }
    }

    private fun shareShow(){
        val shareBody : String = "Check out " + show?.getTitleWithReleaseYear() + "\n" +
                "user vote : " + show?.getUserScoresAsString() + "\n \n" + show?.overview

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, show?.title)
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(shareIntent, "Share ${show?.title} using"))
    }
}