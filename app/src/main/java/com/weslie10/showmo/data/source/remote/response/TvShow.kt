package com.weslie10.showmo.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShow(

    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("original_name")
    val originalName: String,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("backdrop_path")
    val backdropPath: String,
    @field:SerializedName("poster_path")
    val posterPath: String,
    @field:SerializedName("vote_average")
    val voteAverage: Double,
    @field:SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,

    @field:SerializedName("first_air_date")
    val firstAirDate: String,
    @field:SerializedName("last_air_date")
    val lastAirDate: String,

    @field:SerializedName("tagline")
    val tagline: String,
    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    @field:SerializedName("genres")
    var genres: List<GenresItem>,
    @field:SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguagesItem>,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("homepage")
    val homepage: String,
    @field:SerializedName("status")
    val status: String
)

data class SpokenLanguagesItem(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("iso_639_1")
    val iso6391: String,
    @field:SerializedName("english_name")
    val englishName: String
)