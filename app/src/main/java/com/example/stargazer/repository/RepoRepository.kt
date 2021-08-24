package com.example.stargazer.repository

import com.example.stargazer.database.entity.RoomRepo
import com.example.stargazer.repository.repoDataSourse.RepoLocalDataSourse
import com.example.stargazer.repository.repoDataSourse.RepoRemoteDataSourse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoRepository(
    private val repoRemoteDataSourse: RepoRemoteDataSourse,
    private val repoLocalDataSourse: RepoLocalDataSourse
    ) {

    suspend fun fetchRepo(login: String): List<RoomRepo> = withContext(Dispatchers.IO) {
        repoRemoteDataSourse.getRepoList(login)
    }

    suspend fun repoDb(login: String): List<RoomRepo> = repoLocalDataSourse.loadAllRepos(login)
}