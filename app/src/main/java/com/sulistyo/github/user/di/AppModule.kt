package com.sulistyo.github.user.di

import com.sulistyo.github.user.core.domain.usecase.DataInteractor
import com.sulistyo.github.user.core.domain.usecase.DataUseCase
import com.sulistyo.github.user.presentation.detail.DetailUserViewModel
import com.sulistyo.github.user.presentation.favorite.FavoriteViewModel
import com.sulistyo.github.user.presentation.following.FollowingViewModel
import com.sulistyo.github.user.presentation.follows.FollowsViewModel
import com.sulistyo.github.user.presentation.home.HomeViewModel
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
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailUserViewModel(get()) }
    viewModel { FollowsViewModel(get()) }
    viewModel { FollowingViewModel(get()) }
}