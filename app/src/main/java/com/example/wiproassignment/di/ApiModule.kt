package com.example.wiproassignment.di

import com.example.wiproassignment.model.ListDataApi
import com.example.wiproassignment.model.ListDataService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://dl.dropboxusercontent.com"

    @Provides
    fun provideFactsApi(): ListDataApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ListDataApi::class.java)

    }


    @Provides
    fun provideFactsService() : ListDataService {
        return ListDataService()
    }
}