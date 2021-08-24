package com.example.stargazer.repository

import com.example.stargazer.database.entity.RoomStar
import com.example.stargazer.repository.starDataSourse.StarLocalDataSourse
import com.example.stargazer.repository.starDataSourse.StarRemoteDataSourse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StarRepository(
    private val starRemoteDataSourse: StarRemoteDataSourse,
    private val starLocalDataSourse: StarLocalDataSourse
) {

    suspend fun fetchStar(login: String, repo: String): List<RoomStar> = withContext(Dispatchers.IO) {
        starRemoteDataSourse.getStarList(login, repo)
    }
}