package com.sulistyo.github.user.core.domain.usecase

import com.sulistyo.github.user.core.data.remote.ApiResponse
import com.sulistyo.github.user.core.data.remote.model.ResponseDetailUser
import com.sulistyo.github.user.core.data.remote.model.ResponseUserItem
import com.sulistyo.github.user.core.domain.model.UserModel
import com.sulistyo.github.user.core.domain.repository.IDataRepository
import kotlinx.coroutines.flow.Flow

class DataInteractor(private val dataRepository: IDataRepository) : DataUseCase {
    override  fun getFollowers(username: String): Flow<ApiResponse<List<ResponseUserItem>>> =
        dataRepository.getFollowers(username)

    override fun getFollowing(username: String): Flow<ApiResponse<List<ResponseUserItem>>> =
        dataRepository.getFollowing(username)

    override fun setFavorite(user: UserModel, isFavorite: Boolean) =
        dataRepository.setFavorite(user, isFavorite)

    override fun getFavorites(): Flow<List<UserModel>> = dataRepository.getFavorites()

    override suspend fun getUserDetail(username: String): Flow<ApiResponse<ResponseDetailUser>> =
        dataRepository.getUserDetail(username)

    override fun checkUser(id: Int): Int = dataRepository.checkUser(id)

    override fun removeFavorite(id: Int) = dataRepository.removeFavorite(id)

    override suspend fun searchUser(query: String): Flow<ApiResponse<List<ResponseUserItem>>> =
        dataRepository.searchUser(query)
}