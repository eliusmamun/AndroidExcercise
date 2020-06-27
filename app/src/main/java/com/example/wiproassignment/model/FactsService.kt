package com.example.wiproassignment.model

import javax.inject.Inject

class FactsService {

    @Inject
    lateinit var api: FactsApi

    suspend fun getCountries(): Facts {
        return api.getFacts()
    }
}

