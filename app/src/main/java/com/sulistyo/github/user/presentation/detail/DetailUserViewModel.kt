package com.sulistyo.github.user.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulistyo.github.user.core.data.remote.ApiResponse
import com.sulistyo.github.user.core.data.remote.model.ResponseDetailUser
import com.sulistyo.github.user.core.domain.model.UserModel
import com.sulistyo.github.user.core.domain.usecase.DataUseCase
import kotlinx.coroutines.launch

class DetailUserViewModel(private val useCase: DataUseCase) : ViewModel() {

    fun getDetailUser(username: String): LiveData<ApiResponse<ResponseDetailUser>> {
        val result = MutableLiveData<ApiResponse<ResponseDetailUser>>()
        viewModelScope.launch {
            useCase.getUserDetail(username).collect {
                result.postValue(it)
            }
        }
        return result
    }

    fun setFavorite(user: UserModel, isFavorite: Boolean) = useCase.setFavorite(user, isFavorite)

    fun removeFavorite(id: Int) = useCase.removeFavorite(id)

    fun checkUser(id: Int) = useCase.checkUser(id)

}