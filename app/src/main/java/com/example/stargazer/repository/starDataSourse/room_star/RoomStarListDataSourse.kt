package com.example.stargazer.repository.starDataSourse.room_star

import com.example.stargazer.database.dao.StarDao
import com.example.stargazer.database.entity.RoomStar
import com.example.stargazer.repository.starDataSourse.StarLocalDataSourse

class RoomStarListDataSourse(private val starDao: StarDao): StarLocalDataSourse {

    override suspend fun loadAllRepos(login: String, repo: String): List<RoomStar> =
        starDao.getStarList(repo)
}