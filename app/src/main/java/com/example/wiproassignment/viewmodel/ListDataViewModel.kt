package com.example.wiproassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wiproassignment.di.DaggerApiComponent
import com.example.wiproassignment.model.Facts
import com.example.wiproassignment.model.ListDataService
import com.example.wiproassignment.utils.EspressoIdlingResource
import kotlinx.coroutines.*
import javax.inject.Inject


class ListDataViewModel :ViewModel(){

    @Inject
    lateinit var factsService : ListDataService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        CoroutineScope(Dispatchers.Main).launch {
            onError("Exception: ${throwable.localizedMessage}")
        }
    }

    val facts: MutableLiveData<Facts> by lazy {
        fetchFacts()
        MutableLiveData<Facts>()
    }

    val factsLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()


    fun refresh() {
        fetchFacts()
    }

    fun getFactsObservable(): MutableLiveData<Facts> {
        return facts
    }

    /**
     * Fetches the data from the external api
     */
    private fun fetchFacts()  {
        loading.value = true
        EspressoIdlingResource.increment()
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = factsService.getFacts()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    facts.value = response.body()
                    factsLoadError.value = null
                    loading.value = false
                } else {
                    onError("Error: ${response.message()}")
                }
            }
            EspressoIdlingResource.decrement()


        }

    }


    /**
     * Handles the Error
     */
    private fun onError(message: String) {
            factsLoadError.value = message
            loading.value = false
    }

    /**
     * Invokes when viewmodel destroys
     */
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}