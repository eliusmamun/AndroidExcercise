package com.example.wiproassignment.model

import retrofit2.Response
import retrofit2.http.GET

interface ListDataApi {

    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun getFacts(): Response<Facts>

}
