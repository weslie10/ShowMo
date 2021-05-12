package com.weslie10.showmo.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.weslie10.showmo.data.source.local.entities.MovieEntity
import com.weslie10.showmo.data.source.local.entities.MovieResponseEntity
import com.weslie10.showmo.data.source.local.entities.TvShowEntity
import com.weslie10.showmo.data.source.local.entities.TvShowResponseEntity
import com.weslie10.showmo.data.source.local.room.ShowMoDao
import com.weslie10.showmo.utils.SortUtils

class LocalDataSource private constructor(private val showMoDao: ShowMoDao) {

    fun getMovie(sort: String): DataSource.Factory<Int, MovieResponseEntity> {
        val query = SortUtils.getSortedMovie(sort)
        return showMoDao.getSortMovie(query)
    }

    fun getMovieDetail(id: Int): LiveData<MovieEntity> = showMoDao.getDetailMovie(id)

    fun insertListMovie(listMovie: List<MovieResponseEntity>) = showMoDao.insertListMovie(listMovie)

    fun insertMovie(movie: MovieEntity) = showMoDao.insertMovie(movie)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean): Int {
        movie.favorite = newState
        showMoDao.updateMovie(movie)
        return movie.id
    }

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = showMoDao.getFavMovies()

    fun getTvShow(sort: String): DataSource.Factory<Int, TvShowResponseEntity> {
        val query = SortUtils.getSortedTvShow(sort)
        return showMoDao.getSortTvShow(query)
    }

    fun getTvShowDetail(id: Int): LiveData<TvShowEntity> = showMoDao.getDetailTvShow(id)

    fun insertListTvShow(listTvShow: List<TvShowResponseEntity>) =
        showMoDao.insertListTvShow(listTvShow)

    fun insertTvShow(tvShow: TvShowEntity) = showMoDao.insertTvShow(tvShow)

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean): Int {
        tvShow.favorite = newState
        showMoDao.updateTvShow(tvShow)
        return tvShow.id
    }

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = showMoDao.getFavTvShows()

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(showMoDao: ShowMoDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(showMoDao)
    }
}