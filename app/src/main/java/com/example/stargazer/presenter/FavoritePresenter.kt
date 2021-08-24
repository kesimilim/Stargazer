package com.example.stargazer.presenter

import com.example.stargazer.StarApplication
import com.example.stargazer.repository.FavoriteRepository
import com.example.stargazer.view.FavoriteView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class FavoritePresenter: BasePresenter<FavoriteView>() {

    @Inject lateinit var favRepository: FavoriteRepository

    init {
        StarApplication.appComponent.inject(this)
    }

    fun showFavoriteRepos() {
        GlobalScope.launch(Dispatchers.IO) {
            val repo = favRepository.readFavoriteRepos()
            withContext(Dispatchers.Main) {
                viewState.viewFavoriteList(repo)
            }
        }
    }
}