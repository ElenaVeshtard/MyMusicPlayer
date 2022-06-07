package com.example.mymusicplayer.view

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.mymusicplayer.data.DataLoaderFake
import com.example.mymusicplayer.R
import kotlinx.coroutines.*


class AirplaneModeChanged : Service() {

    override fun onCreate() {
        super.onCreate()
        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        CoroutineScope(SupervisorJob() + Dispatchers.Main)

        runBlocking {
            launch {
                startForeground(
                    10, DataLoaderFake().loadNotification(notificationManager, getBuilder())
                )
                DataLoaderFake().loadData(notificationManager, getBuilder())
                stopSelf()
                DataLoaderFake().completeNotification(notificationManager, getBuilder())
            }
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
}
