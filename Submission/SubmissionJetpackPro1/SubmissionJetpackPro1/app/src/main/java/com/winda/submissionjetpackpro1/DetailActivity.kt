package com.winda.submissionjetpackpro1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.winda.submissionjetpackpro1.data.Show
import com.winda.submissionjetpackpro1.databinding.ActivityDetailBinding

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
        binding.tvTagline.text = show?.tagLine
        binding.tvDetailRatings.text = show?.ratings
        binding.tvDetailUserScores.text = show?.getUserScoresAsString()
        binding.tvReleaseDate.text = show?.getReleaseDateAsString()
        binding.tvOverview.text = show?.overview
        binding.tvDetailDuration.text = show?.getDurationAsString()

        // glide poster
        Glide.with(applicationContext)
            .load(show?.posterId)
            .apply(RequestOptions().override(150, 150))
            .placeholder(R.drawable.app_icon_bg)
            .error(R.drawable.app_icon_bg) // will be displayed if the image cannot be loaded
            .dontAnimate()
            .into(binding.imgPoster)

        setEvent()
    }

    private fun setEvent(){
        binding.iconBack.setOnClickListener {
            val backIntent = Intent(applicationContext, CatalogueActivity::class.java)
            startActivity(backIntent)
            finish()
        }

        binding.iconYoutube.setOnClickListener {
            val openYoutubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse(show?.linkTrailer))
            startActivity(openYoutubeIntent)
        }

        binding.iconShare.setOnClickListener {
            shareShow()
        }
    }

    private fun shareShow(){
        val urlYoutube : String? = show?.linkTrailer
        val shareBody : String = "Check out " + show?.getTitleWithReleaseYear() + "\n" + urlYoutube

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, show?.title)
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(shareIntent, "Share ${show?.title} using"))
    }
}