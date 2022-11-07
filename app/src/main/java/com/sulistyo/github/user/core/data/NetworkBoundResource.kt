/*
package com.sulistyo.github.user.core.data

import com.sulistyo.github.user.core.data.remote.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<ApiResource<ResultType>> = flow {
        emit(ApiResource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(ApiResource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { ApiResource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { ApiResource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(ApiResource.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { ApiResource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<ApiResource<ResultType>> = result
}*/
