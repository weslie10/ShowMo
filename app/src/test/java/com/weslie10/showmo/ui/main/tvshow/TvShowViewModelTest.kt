package com.weslie10.showmo.ui.main.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.weslie10.showmo.data.ShowMoRepository
import com.weslie10.showmo.data.source.local.entities.TvShowEntity
import com.weslie10.showmo.data.source.local.entities.TvShowResponseEntity
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel
    private val sort = "ALL"
    private val id = 71712

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: ShowMoRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowResponseEntity>>>

    @Mock
    private lateinit var observerDetail: Observer<Resource<TvShowEntity>>

    @Mock
    private lateinit var pagedListTvShow: PagedList<TvShowResponseEntity>

    @Mock
    private lateinit var pagedListFavorite: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(repository)
        viewModel.setId(id)
    }

    @Test
    fun getTvShow() {
        val dummyTvShows = Resource.success(pagedListTvShow)
        `when`(dummyTvShows.data?.size).thenReturn(20)

        val tvShows = MutableLiveData<Resource<PagedList<TvShowResponseEntity>>>()
        tvShows.value = dummyTvShows

        `when`(repository.getPopularTvShow(sort)).thenReturn(tvShows)
        val listTvShows = viewModel.getTvShow(sort).value?.data
        verify(repository).getPopularTvShow(sort)
        assertNotNull(listTvShows)
        assertEquals(20, listTvShows?.size)

        viewModel.getTvShow(sort).observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }

    @Test
    fun testEmptyTvShow() {
        val dummyTvShows = Resource.success(pagedListTvShow)
        `when`(dummyTvShows.data?.size).thenReturn(0)

        val tvShows = MutableLiveData<Resource<PagedList<TvShowResponseEntity>>>()
        tvShows.value = dummyTvShows

        `when`(repository.getPopularTvShow(sort)).thenReturn(tvShows)
        val listTvShows = viewModel.getTvShow(sort).value?.data
        verify(repository).getPopularTvShow(sort)
        assertNotNull(listTvShows)
        assertEquals(0, listTvShows?.size)
    }

    @Test
    fun testErrorTvShow() {
        val dummyTvShows = Resource.error("Error", pagedListTvShow)

        val tvShows = MutableLiveData<Resource<PagedList<TvShowResponseEntity>>>()
        tvShows.value = dummyTvShows

        `when`(repository.getPopularTvShow(sort)).thenReturn(tvShows)
        val listTvShows = viewModel.getTvShow(sort).value
        assertEquals(dummyTvShows.message, listTvShows?.message)
    }

    @Test
    fun getFavorite() {
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = pagedListFavorite

        `when`(repository.getFavoriteTvShow()).thenReturn(tvShows)
        val listTvShows = viewModel.getFavorite().value
        verify(repository).getFavoriteTvShow()
        assertNotNull(listTvShows)
    }

    @Test
    fun getSpecificTvShow() {
        val dummyTvShows = Resource.error("Error", DataDummy.tvShow)
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShows

        `when`(repository.getTvShow(id)).thenReturn(tvShow)
        viewModel.getSpecificTvShow().observeForever(observerDetail)
        verify(repository).getTvShow(id)
    }

    @Test
    fun testErrorTvShowDetail() {
        val dummyTvShows = Resource.success(DataDummy.tvShow)
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShows

        `when`(repository.getTvShow(id)).thenReturn(tvShow)
        viewModel.getSpecificTvShow().observeForever(observerDetail)
        verify(repository).getTvShow(id)
    }
}