package com.sulistyo.github.user.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserFavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubUserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}