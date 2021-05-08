package com.weslie10.showmo.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "runtime")
    val runtime: Int,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "tagline")
    val tagline: String,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,

    @ColumnInfo(name = "spoken_languages")
    val spokenLanguages: String,
    @ColumnInfo(name = "genres")
    val genres: String,

    @ColumnInfo(name = "homepage")
    val homepage: String,
    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "revenue")
    val revenue: Int,
    @ColumnInfo(name = "budget")
    val budget: Int,
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,
)
