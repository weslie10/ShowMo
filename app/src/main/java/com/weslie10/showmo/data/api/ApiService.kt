package com.weslie10.showmo.data.api

import com.weslie10.showmo.BuildConfig
import com.weslie10.showmo.data.source.remote.response.Movie
import com.weslie10.showmo.data.source.remote.response.MovieResponse
import com.weslie10.showmo.data.source.remote.response.TvShow
import com.weslie10.showmo.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = KEY
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = KEY,
    ): Call<Movie>

    @GET("tv/popular")
    fun getPopularTvShow(
        @Query("api_key") apiKey: String = KEY
    ): Call<TvShowResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") apiKey: String = KEY,
    ): Call<TvShow>

    companion object {
        private const val KEY = BuildConfig.API_KEY
    }
}