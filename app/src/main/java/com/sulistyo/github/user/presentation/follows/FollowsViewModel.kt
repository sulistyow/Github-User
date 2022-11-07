package com.sulistyo.github.user.presentation.follows

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulistyo.github.user.core.data.remote.ApiResponse
import com.sulistyo.github.user.core.data.remote.model.ResponseUserItem
import com.sulistyo.github.user.core.domain.usecase.DataUseCase
import kotlinx.coroutines.launch

class FollowsViewModel(private val useCase: DataUseCase) : ViewModel() {
    private val _followers = MutableLiveData<ApiResponse<List<ResponseUserItem>>>()
    val followers by lazy { _followers }

    fun getFollowers(username: String) {
        viewModelScope.launch {
            useCase.getFollowers(username).collect {
                _followers.postValue(it)
            }
        }
    }
}