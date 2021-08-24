package com.example.stargazer.servise

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class ReminderBroadcast: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Intent(context, AppServise::class.java). also {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startService(it)
            }
        }
    }
}