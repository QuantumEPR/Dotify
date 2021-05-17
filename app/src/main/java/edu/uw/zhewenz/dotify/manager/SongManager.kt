package edu.uw.zhewenz.dotify.manager

import edu.uw.zhewenz.dotify.model.Song
import kotlin.random.Random

class SongManager {
    var selectedSong: Song? = null
        private set

    fun onSongSelected(song: Song) {
        selectedSong = song
    }

    fun selectedStats(song: Song?) : Int {
        return mapOfSongs[song] ?: 0
    }

    fun updateStats(song: Song?, numPlayed: Int) {
        song?.let { mapOfSongs.put(it, numPlayed) }
    }

    fun updateSongs(newListOfSongs: List<Song>) {
        listOfSongs = newListOfSongs
        mapOfSongs = listOfSongs.associateWith { Random.nextInt(0, 1000000) }.toMutableMap()
    }
    var listOfSongs: List<Song> = listOf<Song>()
    var mapOfSongs: MutableMap<Song, Int> = listOfSongs.associateWith { Random.nextInt(0, 1000000) }.toMutableMap()
}