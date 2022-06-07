package com.example.mymusicplayer.data


import android.app.Notification
import android.app.NotificationManager
import androidx.core.app.NotificationCompat

interface DataLoader {
    suspend fun loadData(
        notificationManager: NotificationManager,
        builder: NotificationCompat.Builder
    )

    suspend fun loadNotification(
        notificationManager: NotificationManager,
        builder: NotificationCompat.Builder
    ): Notification

    suspend fun completeNotification(
        notificationManager: NotificationManager,
        builder: NotificationCompat.Builder
    )

    suspend fun createNotificationChannel(notificationManager: NotificationManager)
}
