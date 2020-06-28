package com.example.wiproassignment.di

import com.example.wiproassignment.model.ListDataService
import com.example.wiproassignment.viewmodel.ListDataViewModel
import dagger.Component

 @Component(modules = [ApiModule::class])
    interface ApiComponent {

        fun inject(viewModel: ListDataViewModel)

        fun inject(factsService: ListDataService)

    }