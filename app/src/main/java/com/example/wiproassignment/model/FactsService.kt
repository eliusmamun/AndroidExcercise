package com.example.wiproassignment.model

import javax.inject.Inject

class FactsService {

    @Inject
    lateinit var api: FactsApi

    suspend fun getCountries(): List<Facts> {
        return api.getFacts()
    }
}

