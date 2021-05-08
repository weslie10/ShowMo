package com.weslie10.showmo.di

import android.content.Context
import com.weslie10.showmo.data.ShowMoRepository
import com.weslie10.showmo.data.source.local.LocalDataSource
import com.weslie10.showmo.data.source.local.room.ShowMoDatabase
import com.weslie10.showmo.data.source.remote.RemoteDataSource
import com.weslie10.showmo.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): ShowMoRepository {
        val database = ShowMoDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.dao())
        val appExecutors = AppExecutors()

        return ShowMoRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}