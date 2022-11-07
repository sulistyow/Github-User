package com.sulistyo.github.user.core.data.remote

import android.util.Log
import com.sulistyo.github.user.core.data.remote.model.ResponseDetailUser
import com.sulistyo.github.user.core.data.remote.model.ResponseUserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getUserDetail(username: String): Flow<ApiResponse<ResponseDetailUser>> {
        return flow {
            try {
                val response = apiService.getUserDetail(username)
                emit(ApiResponse.Success(response))

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getFollowers(username: String): Flow<ApiResponse<List<ResponseUserItem>>> {
        return flow {
            try {
                val response = apiService.getFollowers(username)
                val mData = response
                if (mData.isNotEmpty()) {
                    emit(ApiResponse.Success(mData))
                } else {
                    emit(ApiResponse.Error("Data kosong"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("getfollowers : ", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getFollowing(username: String): Flow<ApiResponse<List<ResponseUserItem>>> {
        return flow {
            try {
                val response = apiService.getFollowing(username)
                val mData = response
                if (mData.isNotEmpty()) {
                    emit(ApiResponse.Success(mData))
                } else {
                    emit(ApiResponse.Error("Data kosong"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource: ", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchUser(query: String): Flow<ApiResponse<List<ResponseUserItem>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = apiService.searchUser(query)
                val mData = response.items
                if (mData.isNotEmpty()) {
                    emit(ApiResponse.Success(mData))
                } else {
                    emit(ApiResponse.Error("Data kosong"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource : ", e.message.toString())
            }
        }
    }


}