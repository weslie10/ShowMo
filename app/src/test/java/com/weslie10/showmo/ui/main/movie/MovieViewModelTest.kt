package com.weslie10.showmo.ui.main.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.weslie10.showmo.data.ShowMoRepository
import com.weslie10.showmo.data.source.local.entities.MovieEntity
import com.weslie10.showmo.data.source.local.entities.MovieResponseEntity
import com.weslie10.showmo.utils.DataDummy
import com.weslie10.showmo.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private val sort = "ALL"
    private val movie = DataDummy.movie
    private val id = 399566

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: ShowMoRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieResponseEntity>>>

    @Mock
    private lateinit var observerDetail: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieResponseEntity>

    @Mock
    private lateinit var pagedListFavorite: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
        viewModel.setId(id)
    }

    @Test
    fun getMovie() {
        val dummyMovies = Resource.success(pagedListMovie)
        `when`(dummyMovies.data?.size).thenReturn(20)

        val movies = MutableLiveData<Resource<PagedList<MovieResponseEntity>>>()
        movies.value = dummyMovies

        `when`(repository.getPopularMovie(sort)).thenReturn(movies)
        val listMovies = viewModel.getMovie(sort).value?.data
        verify(repository).getPopularMovie(sort)
        assertNotNull(listMovies)
        assertEquals(20, listMovies?.size)

        viewModel.getMovie(sort).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun testEmptyMovie() {
        val dummyMovies = Resource.success(pagedListMovie)
        `when`(dummyMovies.data?.size).thenReturn(0)

        val movies = MutableLiveData<Resource<PagedList<MovieResponseEntity>>>()
        movies.value = dummyMovies

        `when`(repository.getPopularMovie(sort)).thenReturn(movies)
        val listMovies = viewModel.getMovie(sort).value?.data
        verify(repository).getPopularMovie(sort)
        assertNotNull(listMovies)
        assertEquals(0, listMovies?.size)
    }

    @Test
    fun tesErrorMovie() {
        val dummyMovies = Resource.error(null, pagedListMovie)

        val movies = MutableLiveData<Resource<PagedList<MovieResponseEntity>>>()
        movies.value = dummyMovies

        `when`(repository.getPopularMovie(sort)).thenReturn(movies)
        val listMovies = viewModel.getMovie(sort).value
        assertEquals(dummyMovies.message, listMovies?.message)
    }

    @Test
    fun getFavorite() {
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = pagedListFavorite

        `when`(repository.getFavoriteMovie()).thenReturn(movies)
        val listMovies = viewModel.getFavorite().value
        verify(repository).getFavoriteMovie()
        assertNotNull(listMovies)
    }

    @Test
    fun getSpecificMovie() {
        val dummyMovie = Resource.success(DataDummy.movie)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovie

        `when`(repository.getMovie(id)).thenReturn(movie)
        viewModel.getSpecificMovie().observeForever(observerDetail)
        verify(observerDetail).onChanged(dummyMovie)
    }

    @Test
    fun tesErrorMovieDetail() {
        val dummyMovies = Resource.error(null, pagedListMovie)

        val movies = MutableLiveData<Resource<PagedList<MovieResponseEntity>>>()
        movies.value = dummyMovies

        `when`(repository.getPopularMovie(sort)).thenReturn(movies)
        val listMovies = viewModel.getMovie(sort).value
        assertEquals(dummyMovies.message, listMovies?.message)
    }

    @Test
    fun setFavoriteMovie() {
        `when`(viewModel.setFavorite(movie)).thenReturn(movie.id)

        // set true favorite
        movie.favorite = true
        val addFavorite = viewModel.setFavorite(movie)
        assertNotNull(addFavorite)

        // set false favorite
        movie.favorite = false
        val removeFavorite = viewModel.setFavorite(movie)
        assertNotNull(removeFavorite)
    }
}