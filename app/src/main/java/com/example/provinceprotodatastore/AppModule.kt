package com.example.provinceprotodatastore

import android.app.Application
import android.content.Context
import com.example.provinceprotodatastore.api.ApiClient
import com.example.provinceprotodatastore.api.ProvinceApiService
import com.example.provinceprotodatastore.repository.ProvinceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideProvinceApiService(): ProvinceApiService = ApiClient.service

    @Provides
    @Singleton
    fun provideProvinceRepository(application: Application): ProvinceRepository {
        return ProvinceRepository(application.applicationContext)
    }
}