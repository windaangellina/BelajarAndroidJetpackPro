package com.lukitor.projectandroidjetpackpro1

import androidx.lifecycle.ViewModel
import com.lukitor.projectandroidjetpackpro1.`class`.Data
import com.lukitor.projectandroidjetpackpro1.`class`.Movie

class DetailVieModel : ViewModel(){
    fun getData(title:String): Movie {
        lateinit var temp: Movie
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        judul.forEachIndexed { index, s ->
            if (judul[index] == title){
                val imageID = image[index]
                var datamodel = Movie(s,imageID,description[index],rating[index],genre[index],type[index])
                temp = datamodel
            }
        }
        return temp
    }
}