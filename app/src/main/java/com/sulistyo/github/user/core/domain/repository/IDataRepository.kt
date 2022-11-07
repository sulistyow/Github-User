package com.sulistyo.github.user.core.domain.repository

import com.sulistyo.github.user.core.data.remote.ApiResponse
import com.sulistyo.github.user.core.data.remote.model.ResponseDetailUser
import com.sulistyo.github.user.core.data.remote.model.ResponseUserItem
import com.sulistyo.github.user.core.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

     fun getFollowers(username: String): Flow<ApiResponse<List<ResponseUserItem>>>

    fun getFollowing(username: String): Flow<ApiResponse<List<ResponseUserItem>>>

    fun setFavorite(user: UserModel, isFavorite: Boolean)

    fun getFavorites(): Flow<List<UserModel>>

    suspend fun getUserDetail(username: String): Flow<ApiResponse<ResponseDetailUser>>

    fun checkUser(id: Int): Int

    fun removeFavorite(id: Int)

    suspend fun searchUser(query: String): Flow<ApiResponse<List<ResponseUserItem>>>
}