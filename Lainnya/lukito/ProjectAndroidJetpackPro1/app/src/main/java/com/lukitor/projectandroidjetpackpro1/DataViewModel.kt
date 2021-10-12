package com.lukitor.projectandroidjetpackpro1

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.lukitor.projectandroidjetpackpro1.`class`.Movie
import com.lukitor.projectandroidjetpackpro1.`class`.Data

class DataViewModel : ViewModel(){
    fun getData(tipe:String): ObservableArrayList<Movie>{
        val judul = Data.judul
        val description = Data.description
        val image = Data.image
        val rating = Data.rating
        val genre = Data.genre
        val type = Data.type
        val listData = ObservableArrayList<Movie>()
        judul.forEachIndexed { index, s ->
            if (type[index] == tipe || tipe == "All"){
                val imageID = image[index]
                var datamodel = Movie(s,imageID,description[index],rating[index],genre[index],type[index])
                listData.add(datamodel)
            }
        }
        return listData
    }
}