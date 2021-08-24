package com.example.stargazer.database.dao

import androidx.room.*
import com.example.stargazer.database.entity.RoomFavorite

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavRepo(favRepo: RoomFavorite)

    @Delete
    fun deleteFavRepo(favRepo: RoomFavorite)

    @Query("SELECT * FROM favorite_table ORDER BY id_fav ASC")
    fun readAllFavRepo(): List<RoomFavorite>
}