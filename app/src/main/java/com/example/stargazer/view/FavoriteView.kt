package com.example.stargazer.view

import com.example.stargazer.database.entity.RoomFavorite
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface FavoriteView: MvpView {
    fun viewFavoriteList(repo: List<RoomFavorite>)
    //fun deleteRepo(repo: List<RoomFavorite>)
}