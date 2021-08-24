package com.example.stargazer.database.dao

import androidx.room.*
import com.example.stargazer.database.entity.RoomStar

@Dao
interface StarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStar(star: RoomStar)

    @Update
    fun updateStar(star: RoomStar)

    @Query("SELECT * FROM star_table WHERE repo_star = :repo")
    fun getStarList(repo: String): List<RoomStar>

}