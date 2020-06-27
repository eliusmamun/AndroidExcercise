package com.example.wiproassignment.model

import retrofit2.http.GET

interface FactsApi {

    @GET("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json")
    suspend fun getFacts(): List<Facts>

}
