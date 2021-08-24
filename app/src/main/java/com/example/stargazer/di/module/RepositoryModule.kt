package com.example.stargazer.di.module

import com.example.stargazer.database.dao.FavoriteDao
import com.example.stargazer.database.dao.RepoDao
import com.example.stargazer.database.dao.StarDao
import com.example.stargazer.repository.FavoriteRepository
import com.example.stargazer.repository.RepoRepository
import com.example.stargazer.repository.StarRepository
import com.example.stargazer.repository.favoriteDataSourse.FavoriteLocalDataSourse
import com.example.stargazer.repository.favoriteDataSourse.RoomFavoriteListDataSourse
import com.example.stargazer.repository.repoDataSourse.RepoLocalDataSourse
import com.example.stargazer.repository.repoDataSourse.RepoRemoteDataSourse
import com.example.stargazer.repository.repoDataSourse.retrofit_p2.RetrofitRepoListDataSourse
import com.example.stargazer.repository.repoDataSourse.room_p2.RoomRepoListDataSourse
import com.example.stargazer.repository.starDataSourse.StarLocalDataSourse
import com.example.stargazer.repository.starDataSourse.StarRemoteDataSourse
import com.example.stargazer.repository.starDataSourse.retrofit_star.RetrofitStarListDataSourse
import com.example.stargazer.repository.starDataSourse.room_star.RoomStarListDataSourse
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepoRemoteDataSourse(): RetrofitRepoListDataSourse = RetrofitRepoListDataSourse()

    @Provides
    @Singleton
    fun provideRepoLocalDataSourse(repoDao: RepoDao): RepoLocalDataSourse =
            RoomRepoListDataSourse(repoDao)
    @Provides
    @Singleton
    fun provideRepoRepository(
            remote: RepoRemoteDataSourse,
            local: RepoLocalDataSourse
    ): RepoRepository =
            RepoRepository(remote, local)

    @Singleton
    @Provides
    fun provideStarRemoteDataSourse(): StarRemoteDataSourse = RetrofitStarListDataSourse()

    @Singleton
    @Provides
    fun provideStarLocalDataSourse(starDao: StarDao): StarLocalDataSourse =
            RoomStarListDataSourse(starDao)

    @Singleton
    @Provides
    fun provideStarRepository(
            remote: StarRemoteDataSourse,
            local: StarLocalDataSourse
    ): StarRepository =
            StarRepository(remote, local)

    @Singleton
    @Provides
    fun provideFavLocalDataSourse(favDao: FavoriteDao): FavoriteLocalDataSourse =
            RoomFavoriteListDataSourse(favDao)

    @Singleton
    @Provides
    fun provideFavRepository(
            local: FavoriteLocalDataSourse
    ): FavoriteRepository =
            FavoriteRepository(local)

}