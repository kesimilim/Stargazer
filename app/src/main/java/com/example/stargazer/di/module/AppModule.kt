package com.example.stargazer.di.module

import android.app.Application
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
class AppModule(private val context: Context) {



}