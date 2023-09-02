package com.jeeldesai.android.feedyou.util.work

import android.content.Context
import androidx.work.*
import com.jeeldesai.android.feedyou.data.FeedYouRepository
import java.util.concurrent.TimeUnit

class SweeperWorker(
    context: Context,
    workerParams: WorkerParameters
): Worker(context, workerParams) {

    private val repo = FeedYouRepository.get()

    override fun doWork(): Result {
        repo.deleteLeftoverItems() // Just in case
        return Result.success()
    }

    companion object {
        private const val WORK_NAME = "com.jeeldesai.android.feedyou.utils.work.SweeperWorker"

        fun start(context: Context) {
            val request = PeriodicWorkRequest.Builder(
                SweeperWorker::class.java, 3, TimeUnit.DAYS
            ).build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
        }
    }
}