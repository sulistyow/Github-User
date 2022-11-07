package com.sulistyo.github.user.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sulistyo.github.user.core.domain.usecase.DataUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
@FlowPreview
class HomeViewModel(useCase: DataUseCase) : ViewModel() {

    val queryChannel = MutableStateFlow("")

    val searchResult = queryChannel.debounce(300)
        .distinctUntilChanged()
        .filter { it.trim().isNotEmpty() }
        .flatMapLatest {
            useCase.searchUser(it)
        }
        .asLiveData()

}