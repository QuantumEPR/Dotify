package edu.uw.zhewenz.dotify

import androidx.recyclerview.widget.DiffUtil
import edu.uw.zhewenz.dotify.model.Song

class SongDiffCallback(private val newSongs: List<Song>, private val oldSongs: List<Song>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldSongs.size

    override fun getNewListSize(): Int = newSongs.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newSong = newSongs[newItemPosition]
        val oldSong = oldSongs[oldItemPosition]

        return newSong.id == oldSong.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newSong = newSongs[newItemPosition]
        val oldSong = oldSongs[oldItemPosition]

        return newSong.artist == oldSong.artist && newSong.title == oldSong.title
    }

}