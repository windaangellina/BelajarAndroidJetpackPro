package com.gracielo.jetpacksubmission3v2.TV

import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity

interface TVCallback {
    fun onItemClicked(data: TVEntity)
}