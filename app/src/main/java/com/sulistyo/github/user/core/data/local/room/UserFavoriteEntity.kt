package com.sulistyo.github.user.core.data.local.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_favorite")
@Parcelize
data class UserFavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "gists_url")
    val gistsUrl: String? = null,
    @ColumnInfo(name = "repos_url")
    val reposUrl: String? = null,
    @ColumnInfo(name = "following_url")
    val followingUrl: String? = null,
    @ColumnInfo(name = "starred_url")
    val starredUrl: String? = null,
    @ColumnInfo(name = "login")
    val login: String? = null,
    @ColumnInfo(name = "followers_url")
    val followersUrl: String? = null,
    @ColumnInfo(name = "type")
    val type: String? = null,
    @ColumnInfo(name = "url")
    val url: String? = null,
    @ColumnInfo(name = "subscriptions_url")
    val subscriptionsUrl: String? = null,
    @ColumnInfo(name = "score")
    val score: Double? = null,
    @ColumnInfo(name = "received_events_url")
    val receivedEventsUrl: String? = null,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String? = null,
    @ColumnInfo(name = "events_url")
    val eventsUrl: String? = null,
    @ColumnInfo(name = "html_url")
    val htmlUrl: String? = null,
    @ColumnInfo(name = "site_admin")
    val siteAdmin: Boolean? = null,
    @ColumnInfo(name = "gravatar_id")
    val gravatarId: String? = null,
    @ColumnInfo(name = "node_id")
    val nodeId: String? = null,
    @ColumnInfo(name = "organizations_url")
    val organizationsUrl: String? = null,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
) : Parcelable