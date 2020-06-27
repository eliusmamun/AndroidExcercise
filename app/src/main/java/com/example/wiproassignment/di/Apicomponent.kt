package com.example.wiproassignment.di

import com.example.wiproassignment.viewmodel.FactsViewModel
import dagger.Component


class Apicomponent {

    @Component(modules = [ApiModule::class])
    interface ApiComponent {

        fun inject(viewModel: FactsViewModel)

    }
}