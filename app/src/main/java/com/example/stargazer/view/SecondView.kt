package com.example.stargazer.view

import com.example.stargazer.database.entity.RoomStar
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface SecondView: MvpView {
    fun dataChart(dataList: List<RoomStar>)
    fun buildChart()
    fun addRepoToFavoriteList(login: String, repo: String)
}