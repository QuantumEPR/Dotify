package edu.uw.zhewenz.dotify.manager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import edu.uw.zhewenz.dotify.DotifyApplication


class SongSyncWorker(
    context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {
    private val application by lazy { context.applicationContext as DotifyApplication}
    private val songNotificationManager by lazy { application.songNotificationManager}
    private val songManager by lazy { application.songManager}
    private val dataRepository by lazy { application.dataRepository }

    override suspend fun doWork(): Result {

        Log.i("SongSyncWorker", "syncing songs now")

        // Fetch
        runCatching {
            val musicLibrary = dataRepository.getMusicLibrary()
            val newListOfSongs = musicLibrary.songs
            songManager.updateSongs(newListOfSongs)
            // Publish random song as notification
            songNotificationManager.publishNewSongNotification(songManager.getSongs().random())
        }.onFailure {
            return Result.failure()
        }
        return Result.success()
    }

}