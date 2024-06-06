package com.example.ezlotest.di

import com.example.ezlotest.data.ApiService
import com.example.ezlotest.data.DatabaseRepository
import com.example.ezlotest.data.IDatabaseRepository
import com.example.ezlotest.data.INetworkRepository
import com.example.ezlotest.data.NetworkRepository
import com.example.ezlotest.data.db.DevicesDB
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideNetworkRepository(apiService: ApiService): INetworkRepository {
        return NetworkRepository(apiService)
    }
    @Provides
    fun provideDatabaseRepository(database: DevicesDB): IDatabaseRepository {
        return DatabaseRepository(database)
    }
}