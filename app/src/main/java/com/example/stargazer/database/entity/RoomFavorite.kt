package com.example.stargazer.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class RoomFavorite (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_fav")
    val id: Int = 0,
    val userName: String,
    @ColumnInfo(name = "fav_name")
    val favRepo: String
)