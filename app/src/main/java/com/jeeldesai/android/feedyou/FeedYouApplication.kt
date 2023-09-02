package com.jeeldesai.android.feedyou

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.jeeldesai.android.feedyou.data.FeedYouRepository
import com.jeeldesai.android.feedyou.data.local.FeedPreferences
import com.jeeldesai.android.feedyou.data.local.database.FeedYouDatabase
import com.jeeldesai.android.feedyou.util.NetworkMonitor
import com.jeeldesai.android.feedyou.util.Utils
import com.jeeldesai.android.feedyou.util.work.BackgroundSyncWorker
import com.jeeldesai.android.feedyou.util.work.NewEntriesWorker
import com.jeeldesai.android.feedyou.util.work.SweeperWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedYouApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        FeedPreferences.init(this)
        Utils.setTheme(FeedPreferences.theme)

        val database = FeedYouDatabase.build(this)
        val connectionMonitor = NetworkMonitor(this)
        FeedYouRepository.init(database, connectionMonitor)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NotificationManager::class.java)
            NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            ).let { notificationManager?.createNotificationChannel(it) }
        }

        delayedInit()
    }

    private fun delayedInit() {
        val isPolling = FeedPreferences.shouldPoll
        val isSyncing = FeedPreferences.shouldSyncInBackground

        applicationScope.launch {
            if (isPolling) NewEntriesWorker.start(applicationContext)
            if (isSyncing) BackgroundSyncWorker.start(applicationContext)
            SweeperWorker.start(applicationContext)
        }
    }

    companion object {

        const val NOTIFICATION_CHANNEL_ID = "feedyou_new_entries"
    }
}