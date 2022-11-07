package com.sulistyo.github.user.core.data

import com.sulistyo.github.user.core.data.local.LocalDataSource
import com.sulistyo.github.user.core.data.remote.ApiResponse
import com.sulistyo.github.user.core.data.remote.RemoteDataSource
import com.sulistyo.github.user.core.data.remote.model.ResponseDetailUser
import com.sulistyo.github.user.core.data.remote.model.ResponseUserItem
import com.sulistyo.github.user.core.domain.model.UserModel
import com.sulistyo.github.user.core.domain.repository.IDataRepository
import com.sulistyo.github.user.core.utils.DataMapper
import com.sulistyo.github.user.core.utils.DataMapper.toFavoriteEntity
import com.sulistyo.github.user.utils.AppExecutors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class DataRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IDataRepository {

    override  fun getFollowers(username: String): Flow<ApiResponse<List<ResponseUserItem>>> {
        return remoteDataSource.getFollowers(username)
    }

    override fun getFollowing(username: String): Flow<ApiResponse<List<ResponseUserItem>>> {
        return remoteDataSource.getFollowing(username)
    }

    override fun setFavorite(user: UserModel, isFavorite: Boolean) {
        val favorite = user.toFavoriteEntity()
        appExecutors.diskIO().execute {
            localDataSource.setFavorite(favorite, isFavorite)
        }
    }

    override fun removeFavorite(id: Int) {
        appExecutors.diskIO().execute {
            localDataSource.removeFavorite(id)
        }
    }

    override fun getFavorites(): Flow<List<UserModel>> {
        return localDataSource.getFavorites().map {
            DataMapper.entitiesToDomain(it)
        }
    }

    override suspend fun getUserDetail(username: String): Flow<ApiResponse<ResponseDetailUser>> {
        return remoteDataSource.getUserDetail(username)
    }

    override fun checkUser(id: Int): Int {
        return localDataSource.checkUser(id)
    }

    override suspend fun searchUser(query: String): Flow<ApiResponse<List<ResponseUserItem>>> {
        return remoteDataSource.searchUser(query).flowOn(Dispatchers.IO)
    }


}