package com.example.stargazer.di.module

import android.content.Context
import androidx.room.Room
import com.example.stargazer.database.AppDatabase
import com.example.stargazer.database.dao.FavoriteDao
import com.example.stargazer.database.dao.RepoDao
import com.example.stargazer.database.dao.RepoWithStarDao
import com.example.stargazer.database.dao.StarDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {return context}

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "stargazerDatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun provideRepoDao(database: AppDatabase): RepoDao {
        return database.repoDao()
    }

    @Singleton
    @Provides
    fun provideStarDao(database: AppDatabase): StarDao {
        return database.starDao()
    }

    @Singleton
    @Provides
    fun provideFavDao(database: AppDatabase): FavoriteDao {
        return database.favoriteDao()
    }

    @Singleton
    @Provides
    fun provideRepoWithStarDao(database: AppDatabase): RepoWithStarDao {
        return database.repoWihStarDao()
    }
}