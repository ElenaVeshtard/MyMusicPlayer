package com.example.mymusicplayer.presentation.view

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.mymusicplayer.R
import com.example.mymusicplayer.data.DataLoader
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject


class AirplaneModeChanged : Service() {

    private val dataLoader: DataLoader by inject()
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        coroutineScope.launch {
            startForeground(
                10, dataLoader.loadNotification(notificationManager, getBuilder())
            )
            dataLoader.loadData(notificationManager, getBuilder())
            stopSelf()
            dataLoader.completeNotification(notificationManager, getBuilder())
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun getBuilder(): NotificationCompat.Builder {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Loading")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}
