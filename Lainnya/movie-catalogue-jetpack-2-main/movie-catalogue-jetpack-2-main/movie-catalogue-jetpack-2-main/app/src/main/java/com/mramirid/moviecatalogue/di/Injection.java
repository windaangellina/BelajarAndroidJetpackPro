package com.mramirid.moviecatalogue.di;

import com.mramirid.moviecatalogue.data.source.MovieCatalogueRepository;
import com.mramirid.moviecatalogue.data.source.remote.RemoteRepository;
import com.mramirid.moviecatalogue.utils.JsonHelper;

public class Injection {

	public static MovieCatalogueRepository provideRepository() {
		RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper());
		return MovieCatalogueRepository.getInstance(remoteRepository);
	}
}
