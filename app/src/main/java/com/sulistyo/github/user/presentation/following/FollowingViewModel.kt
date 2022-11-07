package com.sulistyo.github.user.presentation.following

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulistyo.github.user.core.data.remote.ApiResponse
import com.sulistyo.github.user.core.data.remote.model.ResponseUserItem
import com.sulistyo.github.user.core.domain.usecase.DataUseCase
import kotlinx.coroutines.launch

class FollowingViewModel(private val useCase: DataUseCase) : ViewModel() {
    private val _following = MutableLiveData<ApiResponse<List<ResponseUserItem>>>()
    val following by lazy { _following }

    fun getFollowing(username: String) {
        viewModelScope.launch {
            useCase.getFollowing(username).collect {
                _following.postValue(it)
            }
        }
    }
}