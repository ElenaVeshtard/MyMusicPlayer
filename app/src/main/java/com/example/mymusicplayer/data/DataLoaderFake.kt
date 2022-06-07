package com.example.mymusicplayer.data

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.delay

private const val CHANNEL_ID = "message"

class DataLoaderFake : DataLoader {

    override suspend fun loadData(
        notificationManager: NotificationManager,
        builder: NotificationCompat.Builder
    ) {
        loadDataFake(notificationManager, builder)
    }

    override suspend fun loadNotification(
        notificationManager: NotificationManager,
        builder: NotificationCompat.Builder
    ): Notification {
        createNotificationChannel(notificationManager)
        return builder.setContentText("Foreground service").build()
    }

    private suspend fun loadDataFake(
        notificationManager: NotificationManager,
        builder: NotificationCompat.Builder
    ) {
        createNotificationChannel(notificationManager)
        for (i in 1..50) {
            notificationManager.
            notify(10, builder.setProgress(50, i, false).build())
            delay(100)
        }
    }
    override suspend fun completeNotification(
        notificationManager: NotificationManager,
        builder: NotificationCompat.Builder
    ) {
        createNotificationChannel(notificationManager)
        notificationManager.notify(2, builder.setContentText("is complete").build())
    }

    override suspend fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Localized Name"
            val descriptionText = "Localized Description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

}