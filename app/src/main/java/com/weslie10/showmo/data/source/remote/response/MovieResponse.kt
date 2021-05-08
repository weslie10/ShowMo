package com.weslie10.showmo.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("results")
    val results: List<ResultsMovie>
)

data class ResultsMovie(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("poster_path")
    val posterPath: String,
    @field:SerializedName("release_date")
    val releaseDate: String,
)
