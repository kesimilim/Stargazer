package com.example.stargazer.repository.starDataSourse.retrofit_star

import com.example.stargazer.database.entity.RoomStar
import com.example.stargazer.repository.starDataSourse.StarRemoteDataSourse
import com.example.stargazer.retrofit.StarServise

class RetrofitStarListDataSourse: StarRemoteDataSourse {
    override suspend fun getStarList(login: String, repo: String): List<RoomStar> {
        var starList = emptyList<RoomStar>()

        val star = StarServise.starInstance.getListStar(login, repo)
        if(repo != null) {
            starList = star
        }

        return starList
    }
}