package com.sulistyo.github.user.core.data.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setFavorite(user: UserFavoriteEntity)

    @Query("DELETE FROM tb_favorite WHERE tb_favorite.id = :id")
    fun removeFavorite(id: Int)

    @Query("SELECT * FROM tb_favorite")
    fun getFavorites(): Flow<List<UserFavoriteEntity>>

    @Query("SELECT count(*) FROM tb_favorite WHERE tb_favorite.id = :id")
    fun checkUser(id: Int): Int

}