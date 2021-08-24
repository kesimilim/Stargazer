package com.example.stargazer.repository.repoDataSourse

import com.example.stargazer.database.entity.RoomRepo

interface RepoRemoteDataSourse {

    suspend fun getRepoList(login: String): List<RoomRepo>
}