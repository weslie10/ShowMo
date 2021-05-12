package com.weslie10.showmo.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.weslie10.showmo.data.source.local.entities.MovieEntity
import com.weslie10.showmo.data.source.local.entities.MovieResponseEntity
import com.weslie10.showmo.data.source.local.entities.TvShowEntity
import com.weslie10.showmo.data.source.local.entities.TvShowResponseEntity
import com.weslie10.showmo.vo.Resource

interface ShowMoDataSource {
    fun getPopularMovie(sort: String): LiveData<Resource<PagedList<MovieResponseEntity>>>

    fun getMovie(id: Int): LiveData<Resource<MovieEntity>>

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean): Int

    fun getPopularTvShow(sort: String): LiveData<Resource<PagedList<TvShowResponseEntity>>>

    fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean): Int
}