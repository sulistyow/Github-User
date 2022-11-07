package com.sulistyo.github.user.di

import com.sulistyo.github.user.core.domain.usecase.DataInteractor
import com.sulistyo.github.user.core.domain.usecase.DataUseCase
import com.sulistyo.github.user.presentation.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val usecaseModule = module {
    factory<DataUseCase> {
        DataInteractor(get())
    }
}

@OptIn(FlowPreview::class)
@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}