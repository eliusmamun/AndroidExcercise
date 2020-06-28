package com.example.wiproassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wiproassignment.di.DaggerApiComponent
import com.example.wiproassignment.model.Facts
import com.example.wiproassignment.model.ListDataService
import kotlinx.coroutines.*
import javax.inject.Inject


class ListDataViewModel :ViewModel(){

    @Inject
    lateinit var factsService : ListDataService

    init {
        DaggerApiComponent.create().inject(this)
    }

    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    private val facts: MutableLiveData<Facts> by lazy {
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

    private fun fetchFacts()  {
        loading.value = true
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
        }



    }

    private fun onError(message: String) {
        factsLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}