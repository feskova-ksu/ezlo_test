package com.example.ezlotest.di

import android.app.Application
import android.content.Context
import com.example.ezlotest.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,NetworkModule::class, DatabaseModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}

@Module
class AppModule(private val application: Application){
    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext
}