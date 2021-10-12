package com.gracielo.jetpacksubmission3v2.Data.Source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.LocalDataSource
import com.gracielo.jetpacksubmission3v2.Data.NetworkBoundResource
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.ApiResponse
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.RemoteRepository
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response.MovieResponse
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response.TVResponse
import com.gracielo.jetpacksubmission3v2.Utils.AppExecutors
import com.gracielo.jetpacksubmission3v2.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

open class MovieCatalogueRepository private constructor(
    remoteRepository: RemoteRepository,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : FilmDataSource {
    private val remoteRepository: RemoteRepository = remoteRepository

    companion object {
        @Volatile
        private var INSTANCE: MovieCatalogueRepository? = null
        fun getInstance(remoteRepository: RemoteRepository, localDataSource: LocalDataSource, appExecutors: AppExecutors): MovieCatalogueRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MovieCatalogueRepository(remoteRepository, localDataSource, appExecutors).apply {
                    INSTANCE = this
                }
            }
    }

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getListMovies(), config).build()
            }


            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteRepository.getMovies()

            public override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (item in data) {
                    val movie = MovieEntity(
                        item.id,
                        item.judul,
                        item.desc,
                        item.photo,
                        item.kategori,
                        item.tahun,
                        item.rating,
                        item.genre,
                        false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getListFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getListFavoriteMovies(), config).build()
    }

    override fun getTvShows(): LiveData<Resource<PagedList<TVEntity>>> {
        return object : NetworkBoundResource<PagedList<TVEntity>, List<TVResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TVEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getListTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TVEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TVResponse>>> =
                remoteRepository.getTV()

            public override fun saveCallResult(data: List<TVResponse>) {
                val TVList = ArrayList<TVEntity>()
                for (item in data) {
                    val movie = TVEntity(
                        item.id,
                        item.judul,
                        item.desc,
                        item.photo,
                        item.kategori,
                        item.tahun,
                        item.rating,
                        item.genre,
                        false
                    )
                    TVList.add(movie)
                }
                localDataSource.insertTvShows(TVList)
            }

        }.asLiveData()
    }

    override fun getListFavoriteTvShows(): LiveData<PagedList<TVEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getListFavoriteTvShows(), config).build()
    }

    override fun getItemTV(tvShowId: Int): LiveData<TVEntity> =
        localDataSource.getDetailTvShow(tvShowId)

    override fun getItemMovies(id: Int): LiveData<MovieEntity> =
        localDataSource.getDetailMovie(id)

    override fun setFavoriteMovie(movie: MovieEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteMovie(movie)
        }
    }

    override fun setFavoriteTvShow(tvShow: TVEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteTvShow(tvShow)
        }
    }
}