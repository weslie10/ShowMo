package com.weslie10.showmo.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvShow")
data class TvShowEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "original_name")
    val originalName: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "episode_run_time")
    val episodeRunTime: Int,

    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String,
    @ColumnInfo(name = "last_air_date")
    val lastAirDate: String,

    @ColumnInfo(name = "tagline")
    val tagLine: String,
    @ColumnInfo(name = "number_of_seasons")
    val numberOfSeasons: Int,
    @ColumnInfo(name = "genres")
    var genres: String,
    @ColumnInfo(name = "spoken_languages")
    var spokenLanguages: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "homepage")
    val homepage: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,
)