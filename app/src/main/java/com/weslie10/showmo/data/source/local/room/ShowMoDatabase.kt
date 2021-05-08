package com.weslie10.showmo.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.weslie10.showmo.data.source.local.entities.MovieEntity
import com.weslie10.showmo.data.source.local.entities.MovieResponseEntity
import com.weslie10.showmo.data.source.local.entities.TvShowEntity
import com.weslie10.showmo.data.source.local.entities.TvShowResponseEntity

@Database(
    entities = [
        MovieResponseEntity::class,
        MovieEntity::class,
        TvShowResponseEntity::class,
        TvShowEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class ShowMoDatabase : RoomDatabase() {
    abstract fun dao(): ShowMoDao

    companion object {

        @Volatile
        private var INSTANCE: ShowMoDatabase? = null

        fun getInstance(context: Context): ShowMoDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ShowMoDatabase::class.java,
                    "ShowMo.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}