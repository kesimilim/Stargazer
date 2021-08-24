package com.example.stargazer.repository.favoriteDataSourse

import com.example.stargazer.database.dao.FavoriteDao
import com.example.stargazer.database.entity.RoomFavorite

class RoomFavoriteListDataSourse(private val favDao: FavoriteDao): FavoriteLocalDataSourse {
    override fun readFavoriteRepos(): List<RoomFavorite> =
            favDao.readAllFavRepo()

    //override suspend fun addFavoriteRepo(repo: RoomFavorite) = favDao.addFavRepo(repo)
}