package com.sulistyo.github.user.core.data.local

import com.sulistyo.github.user.core.data.local.room.UserDao
import com.sulistyo.github.user.core.data.local.room.UserFavoriteEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val userDao: UserDao) {

    fun setFavorite(user: UserFavoriteEntity, isFavorite: Boolean) {
        user.isFavorite = isFavorite
        userDao.setFavorite(user)
    }

    fun getFavorites(): Flow<List<UserFavoriteEntity>> = userDao.getFavorites()

    fun checkUser(id: Int): Int = userDao.checkUser(id)

}