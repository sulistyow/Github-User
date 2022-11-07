package com.sulistyo.github.user.core.utils

import com.sulistyo.github.user.core.data.local.room.UserFavoriteEntity
import com.sulistyo.github.user.core.data.remote.model.ResponseUserItem
import com.sulistyo.github.user.core.domain.model.UserModel

object DataMapper {

    fun UserModel.toFavoriteEntity() =
        UserFavoriteEntity(
            gistsUrl = gistsUrl,
            reposUrl = reposUrl,
            followingUrl = followingUrl,
            starredUrl = starredUrl,
            login = login,
            followersUrl = followersUrl,
            type = type,
            url = url,
            subscriptionsUrl = subscriptionsUrl,
            score = score,
            receivedEventsUrl = receivedEventsUrl,
            avatarUrl = avatarUrl,
            eventsUrl = eventsUrl,
            htmlUrl = htmlUrl,
            siteAdmin = siteAdmin,
            id = id,
            gravatarId = gravatarId,
            nodeId = nodeId,
            organizationsUrl = organizationsUrl,
            isFavorite = false
        )

    fun entitiesToDomain(list: List<UserFavoriteEntity>): List<UserModel> =
        list.map {
            UserModel(
                gistsUrl = it.gistsUrl,
                reposUrl = it.reposUrl,
                followingUrl = it.followingUrl,
                starredUrl = it.starredUrl,
                login = it.login,
                followersUrl = it.followersUrl,
                type = it.type,
                url = it.url,
                subscriptionsUrl = it.subscriptionsUrl,
                score = it.score,
                receivedEventsUrl = it.receivedEventsUrl,
                avatarUrl = it.avatarUrl,
                eventsUrl = it.eventsUrl,
                htmlUrl = it.htmlUrl,
                siteAdmin = it.siteAdmin,
                id = it.id,
                gravatarId = it.gravatarId,
                nodeId = it.nodeId,
                organizationsUrl = it.organizationsUrl,
                isFavorite = false
            )
        }

    fun responseToDomain(list: List<ResponseUserItem>): List<UserModel> =
        list.map {
            UserModel(
                gistsUrl = it.gistsUrl,
                reposUrl = it.reposUrl,
                followingUrl = it.followingUrl,
                starredUrl = it.starredUrl,
                login = it.login,
                followersUrl = it.followersUrl,
                type = it.type,
                url = it.url,
                subscriptionsUrl = it.subscriptionsUrl,
                score = it.score,
                receivedEventsUrl = it.receivedEventsUrl,
                avatarUrl = it.avatarUrl,
                eventsUrl = it.eventsUrl,
                htmlUrl = it.htmlUrl,
                siteAdmin = it.siteAdmin,
                id = it.id ?: 0,
                gravatarId = it.gravatarId,
                nodeId = it.nodeId,
                organizationsUrl = it.organizationsUrl,
                isFavorite = false
            )
        }

}