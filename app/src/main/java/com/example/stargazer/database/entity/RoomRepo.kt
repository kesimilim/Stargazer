package com.example.stargazer.database.entity

import androidx.room.*
import com.example.stargazer.model.Repo

@Entity(tableName = "repo_table",
    indices = arrayOf(Index(value = arrayOf("repo_name"), unique = true)))
data class RoomRepo (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_repo")
    val id: Int = 0,
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "repo_name")
    override val name: String
): Repo