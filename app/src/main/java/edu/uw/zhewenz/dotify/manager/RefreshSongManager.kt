package edu.uw.zhewenz.dotify.manager

import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

private const val SONG_SYNC_WORK_TAG = "SONG_SYNC_WORK_TAG"
private const val EXTRAS_SYNC_WORK_TAG = "EXTRAS_SYNC_WORK_TAG"

class RefreshSongManager (context: Context) {

    private val workManager: WorkManager = WorkManager.getInstance(context)

    fun startRefreshSongsPeriodically() {
        if (isSongSyncRunning()) {
            return
        }

        val request = PeriodicWorkRequestBuilder<SongSyncWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(SONG_SYNC_WORK_TAG)
            .build()

        workManager.enqueue(request)

    }

    fun stopRefreshSongsPeriodically() {
        Log.i("RefreshSongManager", "Canceling all work by Tag $SONG_SYNC_WORK_TAG")
        workManager.cancelAllWorkByTag(SONG_SYNC_WORK_TAG)
    }

    private fun isSongSyncRunning(): Boolean {
        return workManager.getWorkInfosByTag(SONG_SYNC_WORK_TAG).get().any {
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }

    fun startRefreshExtrasPeriodically() {
        if (isExtrasSyncRunning()) {
            return
        }

        val request = PeriodicWorkRequestBuilder<SongSyncWorker>(2, TimeUnit.DAYS)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(true)
                    .build()
            )
            .addTag(SONG_SYNC_WORK_TAG)
            .build()

        workManager.enqueue(request)

    }

    fun stopRefreshExtrasPeriodically() {
        Log.i("RefreshSongManager", "Canceling all work by Tag $EXTRAS_SYNC_WORK_TAG")
        workManager.cancelAllWorkByTag(EXTRAS_SYNC_WORK_TAG)
    }

    private fun isExtrasSyncRunning(): Boolean {
        return workManager.getWorkInfosByTag(EXTRAS_SYNC_WORK_TAG).get().any {
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }
}