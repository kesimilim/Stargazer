package com.example.stargazer.repository.repoDataSourse

import com.example.stargazer.database.entity.RoomRepo

interface RepoLocalDataSourse {

    suspend fun loadAllRepos(login: String): List<RoomRepo>
}