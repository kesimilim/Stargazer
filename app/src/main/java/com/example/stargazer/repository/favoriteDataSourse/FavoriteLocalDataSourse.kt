package com.example.stargazer.repository.favoriteDataSourse

import com.example.stargazer.database.entity.RoomFavorite

interface FavoriteLocalDataSourse {

    fun readFavoriteRepos(): List<RoomFavorite>

    //suspend fun addFavoriteRepo(repo: RoomFavorite)
}