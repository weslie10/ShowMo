package com.weslie10.showmo.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.weslie10.showmo.data.api.ApiConfig
import com.weslie10.showmo.data.source.remote.response.*
import com.weslie10.showmo.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    fun getMovies(): LiveData<ApiResponse<List<ResultsMovie>>> {
        EspressoIdlingResource.increment()
        val listMovie = MutableLiveData<ApiResponse<List<ResultsMovie>>>()
        val client = ApiConfig.getApiService().getPopularMovies()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    listMovie.value =
                        ApiResponse.success(response.body()?.results as List<ResultsMovie>)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e("getMovie", "Not Success: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("getMovie", "onFailure: ${t.message.toString()}")
            }
        })
        return listMovie
    }

    fun getMovieById(id: Int): LiveData<ApiResponse<Movie>> {
        EspressoIdlingResource.increment()
        val movie = MutableLiveData<ApiResponse<Movie>>()
        val client = ApiConfig.getApiService().getDetailMovie(id)
        client.enqueue(object : Callback<Movie> {
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                if (response.isSuccessful) {
                    val data: ApiResponse<Movie> = ApiResponse.success(response.body() as Movie)
                    data.body.spokenLanguages =
                        response.body()?.spokenLanguages as List<SpokenLanguagesItem>
                    data.body.genres = response.body()?.genres as List<GenresItem>
                    movie.value = data
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e("getMovieById", "Not Success: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("getMovieById", "onFailure: ${t.message.toString()}")
            }
        })
        return movie
    }

    fun getTvShows(): LiveData<ApiResponse<List<ResultsTvShow>>> {
        EspressoIdlingResource.increment()
        val listTvShow = MutableLiveData<ApiResponse<List<ResultsTvShow>>>()
        val client = ApiConfig.getApiService().getPopularTvShow()
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    listTvShow.value =
                        ApiResponse.success(response.body()?.results as List<ResultsTvShow>)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e("getTvShowws", "Not Success: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("getTvShowws", "onFailure: ${t.message.toString()}")
            }
        })
        return listTvShow
    }

    fun getTvShowById(id: Int): LiveData<ApiResponse<TvShow>> {
        EspressoIdlingResource.increment()
        var tvShow = MutableLiveData<ApiResponse<TvShow>>()
        val client = ApiConfig.getApiService().getDetailTvShow(id)
        client.enqueue(object : Callback<TvShow> {
            override fun onResponse(
                call: Call<TvShow>,
                response: Response<TvShow>
            ) {
                if (response.isSuccessful) {
                    val data: ApiResponse<TvShow> = ApiResponse.success(response.body() as TvShow)
                    data.body.spokenLanguages =
                        response.body()?.spokenLanguages as List<SpokenLanguagesItem>
                    data.body.genres = response.body()?.genres as List<GenresItem>
                    tvShow.value = data
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e("getMovieById", "Not Success: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShow>, t: Throwable) {
                Log.e("getMovieById", "onFailure: ${t.message.toString()}")
            }
        })
        return tvShow
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }
}