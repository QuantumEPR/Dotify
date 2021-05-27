package edu.uw.zhewenz.dotify.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import edu.uw.zhewenz.dotify.R
import edu.uw.zhewenz.dotify.activity.PlayerActivity
import edu.uw.zhewenz.dotify.activity.SONG_INFO_KEY
import edu.uw.zhewenz.dotify.model.Song
import kotlin.random.Random

private const val NEW_SONGS_CHANNEL_ID = "NEW_EMAILS_CHANNEL_ID"

class SongNotificationManager(private val context: Context) {
    private val notificationManager = NotificationManagerCompat.from(context)
    var isNotificationEnabled = true

    init {
       initNotificationChannels()
    }

    private fun initNotificationChannels() {
        initNewSongChannel()
    }

    private fun initNewSongChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Info about the channel
            val name = context.getString(R.string.new_music)
            val descriptionText = context.getString(R.string.new_music_channel)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // Create channel object
            val channel = NotificationChannel(NEW_SONGS_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Tell the Android OS to create a channel
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun publishNewSongNotification(song: Song) {
        val intent = Intent(context, PlayerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(SONG_INFO_KEY, song)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(context, NEW_SONGS_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_queue_music_24)
            .setContentTitle("${song.artist} just released a new song!!!")
            .setContentText("Listen now to ${song.title} now on Dotify")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        with(notificationManager) {
            val notificationId = Random.nextInt()
            notify(notificationId,builder.build())
        }
    }
}