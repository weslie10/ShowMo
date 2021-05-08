package com.weslie10.showmo.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Movie(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("release_date")
    val releaseDate: String,
    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,
    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("tagline")
    val tagline: String,
    @field:SerializedName("original_title")
    val originalTitle: String,
    @field:SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguagesItem>,
    @field:SerializedName("genres")
    var genres: List<GenresItem>,
    @field:SerializedName("homepage")
    val homepage: String,
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("revenue")
    val revenue: Int,
    @field:SerializedName("budget")
    val budget: Int,
)

data class GenresItem(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("id")
    val id: Int
)