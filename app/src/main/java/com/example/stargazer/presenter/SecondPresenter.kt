package com.example.stargazer.presenter

import com.example.stargazer.StarApplication
import com.example.stargazer.repository.StarRepository
import com.example.stargazer.view.SecondView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class SecondPresenter: BasePresenter<SecondView>() {
    @Inject lateinit var starRepository: StarRepository

    init {
        StarApplication.appComponent.inject(this)
    }

    fun showStarChart(login: String, repo: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val starList = starRepository.fetchStar(login, repo)
            viewState.dataChart(starList)
            viewState.buildChart()
        }
    }

    fun addFavRepo(login: String, repo: String) {
        viewState.addRepoToFavoriteList(login, repo)
    }
}