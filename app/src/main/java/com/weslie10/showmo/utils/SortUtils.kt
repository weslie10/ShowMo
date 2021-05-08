package com.weslie10.showmo.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val ALL = "All"
    const val TITLE = "Title"
    const val DATE = "Release Date"
    fun getSortedMovie(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieResponse ")
        when (filter) {
            TITLE -> simpleQuery.append("ORDER BY title ASC")
            DATE -> simpleQuery.append("ORDER BY release_date ASC")
            ALL -> simpleQuery.append("ORDER BY id ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedTvShow(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tvShowResponse ")
        when (filter) {
            TITLE -> simpleQuery.append("ORDER BY name ASC")
            DATE -> simpleQuery.append("ORDER BY first_air_date ASC")
            ALL -> simpleQuery.append("ORDER BY id ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}