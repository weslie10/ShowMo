package com.weslie10.showmo.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.weslie10.showmo.data.ShowMoRepository
import com.weslie10.showmo.data.source.local.entities.MovieEntity
import com.weslie10.showmo.data.source.local.entities.MovieResponseEntity
import com.weslie10.showmo.vo.Resource

class MovieViewModel(private val repository: ShowMoRepository) : ViewModel() {
    private val id = MutableLiveData<Int>()

    fun setId(id: Int) {
        this.id.value = id
    }

    fun getMovie(sort: String): LiveData<Resource<PagedList<MovieResponseEntity>>> = repository.getPopularMovie(sort)

    fun getSpecificMovie(): LiveData<Resource<MovieEntity>> = Transformations.switchMap(id) { id ->
        repository.getMovie(id)
    }

    fun getFavorite(): LiveData<PagedList<MovieEntity>> = repository.getFavoriteMovie()

    fun setFavorite(movieEntity: MovieEntity): Int {
        val newState = !movieEntity.favorite
        return repository.setFavoriteMovie(movieEntity, newState)
    }
}