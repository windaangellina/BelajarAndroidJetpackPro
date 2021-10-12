package com.gracielo.jetpacksubmission3v2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Detail.DetailViewModel
import com.gracielo.jetpacksubmission3v2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    lateinit var viewModel : DetailViewModel

    companion object {
        const val ID_FILM = "id"
        const val TYPE_FILM = "type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = obtainViewModel(this)
        val extras = intent.extras
        if (extras != null) {
            val itemId = extras.getInt(ID_FILM, 0)
            val itemType = extras.getString(TYPE_FILM)
            if (itemId != 0 && itemType != null) {
                viewModel.setItemId(itemId)
                viewModel.setItemType(itemType)
            }
            if(itemType == "Movies")
                viewModel.getMovieDetail(itemId).observe(this, { itemEntity: MovieEntity? ->
                    bindData(itemEntity!!,null)
                })
            else{
                viewModel.getTVDetail(itemId).observe(this, { itemEntity: TVEntity? ->
                    bindData(null,itemEntity!!)
                })
            }
        }

        val actionbar = supportActionBar
        actionbar!!.title = "Detail ${extras!!.getString(TYPE_FILM)}"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory: ViewModelFactory? = ViewModelFactory.getInstance(activity)
        return ViewModelProvider(activity, factory!!).get(DetailViewModel::class.java)
    }

    private fun bindData(movieEntity: MovieEntity? , tvEntity: TVEntity?) {
        if(movieEntity!=null){
            val statusFavorite= movieEntity.isFavorite
            binding.txtJudulDet.text = movieEntity.judul
            binding.txtDescDet.text = movieEntity.desc
            binding.txtTahunDet.text = movieEntity.tahun
            Glide.with(this)
                    .load("http://image.tmdb.org/t/p/w500//${movieEntity.photo}")
                    .into(binding.gambarFilmDet)
            statusFavorite.let { status ->
                setFavoriteState(status)
            }
            binding.fabFavorite.setOnClickListener {
                setFavorite(movieEntity, null)
            }
        }
        else{
            val statusFavorite= tvEntity!!.isFavorite
            binding.txtJudulDet.text = tvEntity!!.judul
            binding.txtDescDet.text = tvEntity!!.desc
            binding.txtTahunDet.text = tvEntity!!.tahun
            Glide.with(this)
                    .load("http://image.tmdb.org/t/p/w500//${tvEntity!!.photo}")
                    .into(binding.gambarFilmDet)
            statusFavorite.let { status ->
                setFavoriteState(status)
            }
            binding.fabFavorite.setOnClickListener {
                setFavorite(null, tvEntity)
            }
        }
    }

    private fun setFavoriteState(status: Boolean){
        if (status) {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_true)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_false)
        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }
    private fun setFavorite(movie: MovieEntity?, tvShow: TVEntity?) {
        if (movie != null) {
            if (movie.isFavorite){
                showSnackBar("${movie.judul} Removed from favorite")
            }else {
                showSnackBar("${movie.judul} Added to favorite")
            }
            viewModel.setFavoriteMovie(movie)
        } else {
            if (tvShow != null) {
                if (tvShow.isFavorite){
                    showSnackBar("${tvShow.judul} Removed from favorite")
                }else {
                    showSnackBar("${tvShow.judul} Added to favorite")
                }
                viewModel.setFavoriteTvShow(tvShow)
            }
        }
    }
}