package com.sulistyo.github.user.core.data.remote

import com.sulistyo.github.user.BuildConfig
import com.sulistyo.github.user.core.data.remote.model.ResponseDetailUser
import com.sulistyo.github.user.core.data.remote.model.ResponseFollowers
import com.sulistyo.github.user.core.data.remote.model.ResponseFollowing
import com.sulistyo.github.user.core.data.remote.model.ResponseSearch
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.USER_TOKEN}")
    fun getUserDetail(
        @Path("username") username: String?
    ): ResponseDetailUser

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.USER_TOKEN}")
    fun getFollowers(
        @Path("username") username: String
    ): ResponseFollowers

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.USER_TOKEN}")
    fun getFollowing(
        @Path("username") username: String
    ): ResponseFollowing

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.USER_TOKEN}")
    suspend fun searchUser(@Query("q") query: String): ResponseSearch

}