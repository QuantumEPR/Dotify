package edu.uw.zhewenz.dotify.repository

import com.google.gson.Gson
import edu.uw.zhewenz.dotify.model.MusicLibrary
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// https://raw.githubusercontent.com/echeeUW/codesnippets/master/musiclibrary.json

class DataRepository {
    private val musicService = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MusicService::class.java)

    suspend fun getMusicLibrary(): MusicLibrary = musicService.getMusicLibrary()

}

interface MusicService {
    @GET("echeeUW/codesnippets/master/musiclibrary.json")
    suspend fun getMusicLibrary(): MusicLibrary
}