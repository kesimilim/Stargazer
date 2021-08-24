package com.example.stargazer.servise

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.stargazer.R
import com.example.stargazer.activity.MainActivity

class AppServise: Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this,"AppService.onStartCommand()", Toast.LENGTH_SHORT).show()
        updateStar()
        return START_REDELIVER_INTENT
    }

    override fun onCreate() {
        super.onCreate()
        createNotification()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"AppService.onDestroy()", Toast.LENGTH_SHORT).show()
    }

    private fun updateStar() {

    }

    private fun createNotification(): Notification {
        val notificationChannelId = "STARGAZER SERVISE CHANNEL"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                notificationChannelId,
                "Stargazer Servise notifications channel",
                NotificationManager.IMPORTANCE_HIGH
            ).let {
                it.description = "Stargazer Servise channel"
                it.enableLights(true)
                it.lightColor = Color.RED
                it
            }
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this,"notifyApp")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("New notification")
            .setContentText("Hi!")
            .setAutoCancel(true)
            .setShowWhen(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(200,builder.build())

        return builder.build()
    }
}