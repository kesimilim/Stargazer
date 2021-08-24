package com.example.stargazer.database.dao

import androidx.room.*
import com.example.stargazer.database.entity.RoomRepo

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRepo(repo: RoomRepo)

    @Update
    fun updateRepo(repo: RoomRepo)

    @Query("SELECT * FROM repo_table WHERE user_name LIKE :login")
    suspend fun loadAllRepo(login: String): List<RoomRepo>
}