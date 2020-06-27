package com.example.wiproassignment.di

import com.example.wiproassignment.model.FactsApi
import com.example.wiproassignment.model.FactsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://dl.dropboxusercontent.com"

    @Provides
    fun provideFactsApi(): FactsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FactsApi::class.java)

    }


    @Provides
    fun provideFactsService() : FactsService {
        return FactsService()
    }
}