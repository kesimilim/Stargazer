package com.example.stargazer.di.component

import com.example.stargazer.StarApplication
import com.example.stargazer.activity.FavoriteActivity
import com.example.stargazer.activity.LastActivity
import com.example.stargazer.activity.MainActivity
import com.example.stargazer.activity.SecondActivity
import com.example.stargazer.di.module.AppModule
import com.example.stargazer.di.module.DatabaseModule
import com.example.stargazer.di.module.RepositoryModule
import com.example.stargazer.presenter.FavoritePresenter
import com.example.stargazer.presenter.MainPresenter
import com.example.stargazer.presenter.SecondPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(application: StarApplication)

    fun inject(mainPresenter: MainPresenter)
    fun inject(secondPresenter: SecondPresenter)
    fun inject(favoritePresenter: FavoritePresenter)

    fun inject(mainActivity: MainActivity)
    fun inject(secondActivity: SecondActivity)
    fun inject(lastActivity: LastActivity)
    fun inject(favActivity: FavoriteActivity)
}