package com.example.getuserwithretrofitmvi.data.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.getuserwithretrofitmvi.data.repository.LogRepository
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class LogWorker(
    context: Context,
    workerParam: WorkerParameters,
    private val logRepository: LogRepository
) : CoroutineWorker(context, workerParam) {

    override suspend fun doWork(): Result {
        logRepository.rotate()
        return Result.success()
    }

    companion object {
        fun scheduleDailyRotation(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()

            val request = PeriodicWorkRequestBuilder<LogWorker>(
                1,
                TimeUnit.DAYS
            )
                .setConstraints(constraints)
                .setInitialDelay(calculateTime())
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "LogRotation",
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
        }

        private fun calculateTime(): Duration {
            val now = LocalDateTime.now()
            val next = now.withHour(21).withMinute(0).withSecond(0)
            val delay = if (now.isAfter(next)) next.plusDays(1) else next
            val millis = Duration.between(now, delay).toMillis()
            return Duration.ofMillis(millis)
        }
    }

}
