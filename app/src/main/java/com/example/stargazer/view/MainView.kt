package com.example.stargazer.view

import com.example.stargazer.database.entity.RoomRepo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface MainView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun viewRepos(repos: List<RoomRepo>)
    fun viewError()
}