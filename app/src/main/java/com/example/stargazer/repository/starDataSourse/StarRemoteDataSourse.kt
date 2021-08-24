package com.example.stargazer.repository.starDataSourse

import com.example.stargazer.database.entity.RoomStar

interface StarRemoteDataSourse {

    suspend fun getStarList(login: String, repo: String): List<RoomStar>
}