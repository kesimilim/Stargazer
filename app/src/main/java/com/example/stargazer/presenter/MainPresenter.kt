package com.example.stargazer.presenter

import com.example.stargazer.StarApplication
import com.example.stargazer.repository.RepoRepository
import com.example.stargazer.view.MainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MainPresenter: BasePresenter<MainView>() {

    @Inject lateinit var repoRepository: RepoRepository

    init {
        StarApplication.appComponent.inject(this)
    }

    fun showRepos(login: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val repo = repoRepository.fetchRepo(login)
            viewState.viewRepos(repo)
        }
    }

}