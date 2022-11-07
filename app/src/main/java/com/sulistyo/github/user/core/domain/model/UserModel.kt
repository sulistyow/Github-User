package com.sulistyo.github.user.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val gistsUrl: String? = null,
    val reposUrl: String? = null,
    val followingUrl: String? = null,
    val starredUrl: String? = null,
    val login: String? = null,
    val followersUrl: String? = null,
    val type: String? = null,
    val url: String? = null,
    val subscriptionsUrl: String? = null,
    val score: Double? = null,
    val receivedEventsUrl: String? = null,
    val avatarUrl: String? = null,
    val eventsUrl: String? = null,
    val htmlUrl: String? = null,
    val siteAdmin: Boolean? = null,
    val id: Int,
    val gravatarId: String? = null,
    val nodeId: String? = null,
    val organizationsUrl: String? = null,
    val isFavorite: Boolean = false
) : Parcelable
