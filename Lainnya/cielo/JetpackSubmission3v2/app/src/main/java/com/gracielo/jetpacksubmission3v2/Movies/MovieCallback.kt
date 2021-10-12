package com.gracielo.jetpacksubmission3v2.Movies

import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity

interface MovieCallback {
    fun onItemClicked(data: MovieEntity)
}