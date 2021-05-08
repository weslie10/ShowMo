package com.weslie10.showmo.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.weslie10.showmo.data.source.local.LocalDataSource
import com.weslie10.showmo.data.source.local.entities.MovieEntity
import com.weslie10.showmo.data.source.local.entities.MovieResponseEntity
import com.weslie10.showmo.data.source.local.entities.TvShowEntity
import com.weslie10.showmo.data.source.local.entities.TvShowResponseEntity
import com.weslie10.showmo.data.source.remote.RemoteDataSource
import com.weslie10.showmo.utils.AppExecutors
import com.weslie10.showmo.utils.DataDummy
import com.weslie10.showmo.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ShowMoRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val showMoRepository = FakeShowMoRepository(remote, local, appExecutors)

    private val sort = "ALL"

    private val listMovie = DataDummy.moviePopular
    private val movie = DataDummy.movie
    private val movieId = 399566

    private val listTvShows = DataDummy.tvShowPopular
    private val tvShow = DataDummy.tvShow
    private val tvShowId = 71712

    @Test
    fun getMoviePopular() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieResponseEntity>
        `when`(local.getMovie(sort)).thenReturn(dataSourceFactory)
        showMoRepository.getPopularMovie(sort)

        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(listMovie.results))
        verify(local).getMovie(sort)
        assertNotNull(moviesEntities)
        assertEquals(listMovie.results.size, moviesEntities.data?.size)
    }

    @Test
    fun getTvShowPopular() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowResponseEntity>
        `when`(local.getTvShow(sort)).thenReturn(dataSourceFactory)
        showMoRepository.getPopularTvShow(sort)

        val tvShowsEntities = Resource.success(PagedListUtil.mockPagedList(listTvShows.results))
        verify(local).getTvShow(sort)
        assertNotNull(tvShowsEntities)
        assertEquals(listTvShows.results.size, tvShowsEntities.data?.size)
    }

    @Test
    fun getMovie() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movie
        `when`(local.getMovieDetail(movieId)).thenReturn(dummyMovie)

        val movieEntities = LiveDataTestUtil.getValue(showMoRepository.getMovie(movieId))
        verify(local).getMovieDetail(movieId)

        assertNotNull(movieEntities)
        assertEquals(movie.id, movieEntities.data?.id)
        assertEquals(movie.backdropPath, movieEntities.data?.backdropPath)
        assertEquals(movie.budget, movieEntities.data?.budget)
        assertEquals(movie.genres, movieEntities.data?.genres)
        assertEquals(movie.homepage, movieEntities.data?.homepage)
        assertEquals(movie.originalTitle, movieEntities.data?.originalTitle)
        assertEquals(movie.overview, movieEntities.data?.overview)
        assertEquals(movie.posterPath, movieEntities.data?.posterPath)
        assertEquals(movie.releaseDate, movieEntities.data?.releaseDate)
        assertEquals(movie.revenue, movieEntities.data?.revenue)
        assertEquals(movie.runtime, movieEntities.data?.runtime)
        assertEquals(movie.spokenLanguages, movieEntities.data?.spokenLanguages)
        assertEquals(movie.status, movieEntities.data?.status)
        assertEquals(movie.tagline, movieEntities.data?.tagline)
        assertEquals(movie.title, movieEntities.data?.title)
        assertEquals(movie.voteAverage.toString(), movieEntities.data?.voteAverage.toString())
    }

    @Test
    fun getTvShow() {
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = tvShow
        `when`(local.getTvShowDetail(tvShowId)).thenReturn(dummyTvShow)

        val tvShowEntity = LiveDataTestUtil.getValue(showMoRepository.getTvShow(tvShowId))
        verify(local).getTvShowDetail(tvShowId)

        assertNotNull(tvShowEntity)
        assertEquals(tvShow.id, tvShowEntity.data?.id)
        assertEquals(tvShow.name, tvShowEntity.data?.name)
        assertEquals(tvShow.originalName, tvShowEntity.data?.originalName)
        assertEquals(tvShow.overview, tvShowEntity.data?.overview)
        assertEquals(tvShow.backdropPath, tvShowEntity.data?.backdropPath)
        assertEquals(tvShow.posterPath, tvShowEntity.data?.posterPath)
        assertEquals(tvShow.voteAverage.toString(), tvShowEntity.data?.voteAverage.toString())
        assertEquals(tvShow.episodeRunTime, tvShowEntity.data?.episodeRunTime)
        assertEquals(tvShow.firstAirDate, tvShowEntity.data?.firstAirDate)
        assertEquals(tvShow.lastAirDate, tvShowEntity.data?.lastAirDate)
        assertEquals(tvShow.tagLine, tvShowEntity.data?.tagLine)
        assertEquals(tvShow.numberOfSeasons, tvShowEntity.data?.numberOfSeasons)
        assertEquals(tvShow.genres, tvShowEntity.data?.genres)
        assertEquals(tvShow.spokenLanguages, tvShowEntity.data?.spokenLanguages)
        assertEquals(tvShow.type, tvShowEntity.data?.type)
        assertEquals(tvShow.homepage, tvShowEntity.data?.homepage)
        assertEquals(tvShow.status, tvShowEntity.data?.status)
    }
}