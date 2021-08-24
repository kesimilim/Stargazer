package com.example.stargazer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.stargazer.database.dao.FavoriteDao
import com.example.stargazer.database.dao.RepoDao
import com.example.stargazer.database.dao.RepoWithStarDao
import com.example.stargazer.database.dao.StarDao
import com.example.stargazer.database.entity.RoomFavorite
import com.example.stargazer.database.entity.RoomRepo
import com.example.stargazer.database.entity.RoomStar

@Database(entities = [RoomRepo::class, RoomStar::class, RoomFavorite::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun repoDao(): RepoDao
    abstract fun starDao(): StarDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun repoWihStarDao(): RepoWithStarDao
}