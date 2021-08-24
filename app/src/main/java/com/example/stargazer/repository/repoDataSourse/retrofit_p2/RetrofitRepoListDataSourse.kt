package com.example.stargazer.repository.repoDataSourse.retrofit_p2

import com.example.stargazer.database.entity.RoomRepo
import com.example.stargazer.repository.repoDataSourse.RepoRemoteDataSourse
import com.example.stargazer.retrofit.RepoServise

class RetrofitRepoListDataSourse: RepoRemoteDataSourse {

    override suspend fun getRepoList(login: String): List<RoomRepo> {
        var repos = emptyList<RoomRepo>()

        val repo = RepoServise.reposInstance.getListRepos(login)
        if(repo != null) {
            repos = repo
        } else {
            val repoOrg = RepoServise.reposInstance.getListReposOrg(login)
            if(repoOrg != null) {
                repos = repoOrg
            }
        }

        return repos
    }

}
