package com.example.stargazer.repository

import com.example.stargazer.database.entity.RoomFavorite
import com.example.stargazer.repository.favoriteDataSourse.FavoriteLocalDataSourse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteRepository(
        private val favLocalDataSourse: FavoriteLocalDataSourse
) {

    fun readFavoriteRepos(): List<RoomFavorite> = favLocalDataSourse.readFavoriteRepos()

}