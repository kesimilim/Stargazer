package com.example.stargazer.repository.starDataSourse

import com.example.stargazer.database.entity.RoomRepo
import com.example.stargazer.database.entity.RoomStar

interface StarLocalDataSourse {
    suspend fun loadAllRepos(login: String, repo: String): List<RoomStar>
}