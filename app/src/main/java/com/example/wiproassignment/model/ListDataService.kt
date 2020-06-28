package com.example.wiproassignment.model

import com.example.wiproassignment.di.DaggerApiComponent
import retrofit2.Response
import javax.inject.Inject

class ListDataService {

    @Inject
    lateinit var api: ListDataApi

    init {
        DaggerApiComponent.create().inject(this)
    }


    suspend fun getFacts(): Response<Facts> {
        return api.getFacts()
    }
}

