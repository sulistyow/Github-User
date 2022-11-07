package com.sulistyo.github.user.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sulistyo.github.user.core.domain.usecase.DataUseCase

class FavoriteViewModel(useCase: DataUseCase) : ViewModel() {

    val favorites = useCase.getFavorites().asLiveData()

}