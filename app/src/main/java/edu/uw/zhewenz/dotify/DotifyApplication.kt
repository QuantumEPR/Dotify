package edu.uw.zhewenz.dotify

import android.app.Application
import edu.uw.zhewenz.dotify.manager.SongManager
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
    lateinit var dataRepository: DataRepository
    override fun onCreate() {
        super.onCreate()
        songManager = SongManager()
        dataRepository = DataRepository()
    }
}