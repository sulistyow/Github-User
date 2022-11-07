package com.sulistyo.github.user.core.data.local.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Update
    fun setFavorite(user: UserFavoriteEntity)

    @Query("SELECT * FROM tb_favorite WHERE is_favorite = 1")
    fun getFavorites(): Flow<List<UserFavoriteEntity>>

    @Query("SELECT count(*) FROM tb_favorite WHERE tb_favorite.id = :id")
    fun checkUser(id: Int): Int

}