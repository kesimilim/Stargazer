package com.example.stargazer.repository.repoDataSourse.room_p2

import com.example.stargazer.database.dao.RepoDao
import com.example.stargazer.database.entity.RoomRepo
import com.example.stargazer.repository.repoDataSourse.RepoLocalDataSourse

class RoomRepoListDataSourse(val repoDao: RepoDao): RepoLocalDataSourse {
    override suspend fun loadAllRepos(login: String): List<RoomRepo> = repoDao.loadAllRepo(login)
}