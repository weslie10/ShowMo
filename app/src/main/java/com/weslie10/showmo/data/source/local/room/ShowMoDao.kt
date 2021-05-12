package com.weslie10.showmo.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.weslie10.showmo.data.source.local.entities.MovieEntity
import com.weslie10.showmo.data.source.local.entities.MovieResponseEntity
import com.weslie10.showmo.data.source.local.entities.TvShowEntity
import com.weslie10.showmo.data.source.local.entities.TvShowResponseEntity

@Dao
interface ShowMoDao {
    //movie
    @Query("SELECT * FROM movieResponse")
    fun getMovies(): DataSource.Factory<Int, MovieResponseEntity>

    @RawQuery(observedEntities = [MovieResponseEntity::class])
    fun getSortMovie(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieResponseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListMovie(listMovie: List<MovieResponseEntity>)

    @Query("SELECT * FROM movie WHERE id=:id")
    fun getDetailMovie(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Update
    fun updateMovie(movie: MovieEntity): Int

    @Query("SELECT * FROM movie WHERE favorite = 1")
    fun getFavMovies(): DataSource.Factory<Int, MovieEntity>

    //tvShow
    @Query("SELECT * FROM tvShowResponse")
    fun getTvShows(): DataSource.Factory<Int, TvShowResponseEntity>

    @RawQuery(observedEntities = [TvShowResponseEntity::class])
    fun getSortTvShow(query: SupportSQLiteQuery): DataSource.Factory<Int, TvShowResponseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListTvShow(listTvShows: List<TvShowResponseEntity>)

    @Query("SELECT * FROM tvShow WHERE id=:id")
    fun getDetailTvShow(id: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: TvShowEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity): Int

    @Query("SELECT * FROM tvShow WHERE favorite = 1")
    fun getFavTvShows(): DataSource.Factory<Int, TvShowEntity>
}