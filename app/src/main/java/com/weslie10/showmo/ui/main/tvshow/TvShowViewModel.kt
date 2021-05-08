package com.weslie10.showmo.ui.main.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.weslie10.showmo.data.ShowMoRepository
import com.weslie10.showmo.data.source.local.entities.TvShowEntity
import com.weslie10.showmo.data.source.local.entities.TvShowResponseEntity
import com.weslie10.showmo.vo.Resource

class TvShowViewModel(private val repository: ShowMoRepository) : ViewModel() {
    val id = MutableLiveData<Int>()

    fun setId(id: Int) {
        this.id.value = id
    }

    fun getTvShow(sort: String): LiveData<Resource<PagedList<TvShowResponseEntity>>> =
        repository.getPopularTvShow(sort)

    fun getSpecificTvShow(): LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(id) { id ->
            repository.getTvShow(id)
        }

    fun getFavorite(): LiveData<PagedList<TvShowEntity>> = repository.getFavoriteTvShow()

    fun setFavorite(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.favorite
        repository.setFavoriteTvShow(tvShowEntity, newState)
    }
}