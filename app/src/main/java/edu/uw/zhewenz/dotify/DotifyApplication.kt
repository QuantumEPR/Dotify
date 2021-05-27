package edu.uw.zhewenz.dotify

import android.app.Application
import android.app.NotificationManager
import edu.uw.zhewenz.dotify.manager.RefreshSongManager
import edu.uw.zhewenz.dotify.manager.SongManager
import edu.uw.zhewenz.dotify.manager.SongNotificationManager
import edu.uw.zhewenz.dotify.model.AppInfo
import edu.uw.zhewenz.dotify.model.Person
import edu.uw.zhewenz.dotify.repository.DataRepository

class DotifyApplication: Application() {
    val myCharacter: Person = Person(
            profilePic = R.drawable.greater_dog,
            name = "Greater Dog",
            age = 25,
            email = "greaterDog@undertale.com",
            date = "09/15/15",
            height = "?"
    )

    val myAppInfo: AppInfo = AppInfo(
            devName = "Zhewen Zheng",
            version = BuildConfig.VERSION_NAME,
            github = "https://github.com/QuantumEPR"
    )

    lateinit var songManager: SongManager
    lateinit var refreshSongManager: RefreshSongManager
    lateinit var songNotificationManager: SongNotificationManager
    lateinit var dataRepository: DataRepository
    override fun onCreate() {
        super.onCreate()
        songManager = SongManager()
        refreshSongManager = RefreshSongManager(this)
        songNotificationManager = SongNotificationManager(this)
        dataRepository = DataRepository()
    }
}