package com.example.stargazer

import android.app.*
import android.app.AlarmManager
import android.app.AlarmManager.INTERVAL_DAY
import android.content.Context
import android.content.Intent
import androidx.room.Database
import com.example.stargazer.database.AppDatabase
import com.example.stargazer.di.component.AppComponent
import com.example.stargazer.di.component.DaggerAppComponent
import com.example.stargazer.di.module.AppModule
import com.example.stargazer.di.module.DatabaseModule
import com.example.stargazer.di.module.RepositoryModule
import com.example.stargazer.servise.ReminderBroadcast
import java.util.*

class StarApplication: Application() {

    companion object {

        lateinit var appComponent: AppComponent

    }
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule(this))
            .repositoryModule(RepositoryModule())
            .build()
        appComponent.inject(this)

        alarmManager()
    }

    fun component(): DaggerAppComponent {
        return appComponent as DaggerAppComponent
    }

    private fun alarmManager() {
        var alarmManager: AlarmManager? = null

        val intent = Intent(this, ReminderBroadcast::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 12)
            set(Calendar.MINUTE, 30)
        }

        alarmManager?.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            INTERVAL_DAY,
            pendingIntent
        )
    }

}