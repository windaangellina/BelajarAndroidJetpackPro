package com.mramirid.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;

import com.mramirid.moviecatalogue.data.entity.ItemEntity;

import java.util.ArrayList;

public interface MovieCatalogueDataSource {
	LiveData<ArrayList<ItemEntity>> getMovies();
	LiveData<ArrayList<ItemEntity>> getTvShows();
	LiveData<ItemEntity> getItem(String itemType, int id);
}
