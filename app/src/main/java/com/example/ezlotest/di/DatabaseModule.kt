package com.example.ezlotest.di

import android.content.Context
import androidx.room.Room
import com.example.ezlotest.data.db.DevicesDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDevicesDB(context: Context): DevicesDB {
        return Room.databaseBuilder(context, DevicesDB::class.java, DB_NAME).build()
    }

    companion object {
        const val DB_NAME = "DevicesDB"
    }
}
