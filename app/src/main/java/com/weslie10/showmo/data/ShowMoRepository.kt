package com.weslie10.showmo.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.weslie10.showmo.data.source.local.LocalDataSource
import com.weslie10.showmo.data.source.local.entities.MovieEntity
import com.weslie10.showmo.data.source.local.entities.MovieResponseEntity
import com.weslie10.showmo.data.source.local.entities.TvShowEntity
import com.weslie10.showmo.data.source.local.entities.TvShowResponseEntity
import com.weslie10.showmo.data.source.remote.ApiResponse
import com.weslie10.showmo.data.source.remote.RemoteDataSource
import com.weslie10.showmo.data.source.remote.response.Movie
import com.weslie10.showmo.data.source.remote.response.ResultsMovie
import com.weslie10.showmo.data.source.remote.response.ResultsTvShow
import com.weslie10.showmo.data.source.remote.response.TvShow
import com.weslie10.showmo.utils.AppExecutors
import com.weslie10.showmo.vo.Resource

class ShowMoRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ShowMoDataSource {

    override fun getPopularMovie(sort: String): LiveData<Resource<PagedList<MovieResponseEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieResponseEntity>, List<ResultsMovie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieResponseEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovie(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieResponseEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsMovie>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<ResultsMovie>) {
                val listMovie = ArrayList<MovieResponseEntity>()
                for (response in data) {
                    val movie = MovieResponseEntity(
                        response.id,
                        response.title,
                        response.overview,
                        response.posterPath,
                        response.releaseDate,
                    )
                    listMovie.add(movie)
                }
                localDataSource.insertListMovie(listMovie)
            }
        }.asLiveData()
    }

    override fun getMovie(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, Movie>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieDetail(id)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<Movie>> =
                remoteDataSource.getMovieById(id)

            override fun saveCallResult(data: Movie) {
                val lang = data.spokenLanguages.joinToString { it.englishName }
                val genre = data.genres.joinToString { it.name }
                val movie = MovieEntity(
                    data.id,
                    data.overview,
                    data.title,
                    data.releaseDate,
                    data.runtime,
                    data.voteAverage,
                    data.backdropPath,
                    data.posterPath,
                    data.tagline,
                    data.originalTitle,
                    lang,
                    genre,
                    data.homepage,
                    data.status,
                    data.revenue,
                    data.budget,
                )
                localDataSource.insertMovie(movie)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) : Int {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, state) }
        return movie.id
    }

    override fun getPopularTvShow(sort: String): LiveData<Resource<PagedList<TvShowResponseEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowResponseEntity>, List<ResultsTvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowResponseEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShow(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowResponseEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsTvShow>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<ResultsTvShow>) {
                val listTvShow = ArrayList<TvShowResponseEntity>()
                for (response in data) {
                    val tvShow = TvShowResponseEntity(
                        response.id,
                        response.name,
                        response.overview,
                        response.posterPath,
                        response.firstAirDate,
                    )
                    listTvShow.add(tvShow)
                }
                localDataSource.insertListTvShow(listTvShow)
            }
        }.asLiveData()
    }

    override fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShow>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowDetail(id)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvShow>> =
                remoteDataSource.getTvShowById(id)

            override fun saveCallResult(data: TvShow) {
                val lang = data.spokenLanguages.joinToString { it.englishName }
                val genre = data.genres.joinToString { it.name }
                val runtime =
                    if (data.episodeRunTime.isNotEmpty()) data.episodeRunTime.first() else 0
                val tvShow = TvShowEntity(
                    data.id,
                    data.name,
                    data.originalName,
                    data.overview,
                    data.backdropPath,
                    data.posterPath,
                    data.voteAverage,
                    runtime,
                    data.firstAirDate,
                    data.lastAirDate,
                    data.tagline,
                    data.numberOfSeasons,
                    genre,
                    lang,
                    data.type,
                    data.homepage,
                    data.status,
                )
                localDataSource.insertTvShow(tvShow)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean): Int {
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow, state) }
        return tvShow.id
    }

    companion object {
        @Volatile
        private var instance: ShowMoRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): ShowMoRepository =
            instance ?: synchronized(this) {
                instance ?: ShowMoRepository(
                    remoteData,
                    localData,
                    appExecutors
                ).apply { instance = this }
            }
    }
}