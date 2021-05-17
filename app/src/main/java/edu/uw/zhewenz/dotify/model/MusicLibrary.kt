package edu.uw.zhewenz.dotify.model

data class MusicLibrary(
    val title: String,
    val numOfSongs: Int,
    val songs: List<Song>
)