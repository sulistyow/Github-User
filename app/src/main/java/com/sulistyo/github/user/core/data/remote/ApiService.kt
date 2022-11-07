package com.sulistyo.github.user.core.data.remote

import com.sulistyo.github.user.BuildConfig
import com.sulistyo.github.user.core.data.remote.model.*
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.USER_TOKEN}")
    suspend fun getUserDetail(
        @Path("username") username: String?
    ): ResponseDetailUser

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.USER_TOKEN}")
    suspend fun getFollowers(
        @Path("username") username: String
    ): List<ResponseUserItem>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.USER_TOKEN}")
    suspend fun getFollowing(
        @Path("username") username: String
    ): List<ResponseUserItem>

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.USER_TOKEN}")
    suspend fun searchUser(@Query("q") query: String): ResponseSearch

}