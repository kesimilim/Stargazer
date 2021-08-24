package com.example.stargazer.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.stargazer.database.entity.RoomStar

@Dao
interface RepoWithStarDao {
    @Transaction
    @Query("SELECT * FROM star_table WHERE repo_star = :repo")
    fun getStarList(repo: String): List<RoomStar>
}