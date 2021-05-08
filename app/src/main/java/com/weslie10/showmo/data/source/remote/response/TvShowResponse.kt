package com.weslie10.showmo.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @field:SerializedName("results")
    val results: List<ResultsTvShow>
)

data class ResultsTvShow(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("poster_path")
    val posterPath: String,
    @field:SerializedName("first_air_date")
    val firstAirDate: String,
)
