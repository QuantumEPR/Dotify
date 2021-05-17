package edu.uw.zhewenz.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import edu.uw.zhewenz.dotify.model.Song
import edu.uw.zhewenz.dotify.DotifyApplication
import edu.uw.zhewenz.dotify.R
import edu.uw.zhewenz.dotify.adapter.SongListAdapter
import edu.uw.zhewenz.dotify.databinding.ActivitySongListBinding
import edu.uw.zhewenz.dotify.manager.SongManager
import edu.uw.zhewenz.dotify.repository.DataRepository
import kotlinx.coroutines.launch

class SongListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongListBinding
    private lateinit var dotifyApp: DotifyApplication
    private lateinit var dataRepository: DataRepository
    private lateinit var songManager: SongManager
    private lateinit var adapter: SongListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater).apply{ setContentView(root) }
        this.dotifyApp = this.applicationContext as DotifyApplication
        this.songManager = dotifyApp.songManager
        this.dataRepository = dotifyApp.dataRepository

        with(binding) {
            val listOfSongs = songManager.listOfSongs

            adapter = SongListAdapter(listOfSongs)
            rvSongs.adapter = adapter


            if(savedInstanceState != null) {
                val song = savedInstanceState.getParcelable<Song?>(STATE_SELECTED_SONG)
                if (song != null) {
                    songManager.onSongSelected(song)
                    miniPlayerGroup.visibility = View.VISIBLE
                    songDescription.text = resources.getString(R.string.song_description, song.title, song.artist)
                }
            } else {
                loadSongs()
            }

            adapter.onSongClickListener = { song ->
                songManager.onSongSelected(song)
                miniPlayerGroup.visibility = View.VISIBLE
                songDescription.text = resources.getString(R.string.song_description, song.title, song.artist)
            }

            adapter.onSongLongClickListener = { position, song ->
                val newListOfSongs = listOfSongs.toMutableList().apply {
                    removeAt(position)
                }
                Toast.makeText(this@SongListActivity,"${song.title} was deleted", Toast.LENGTH_SHORT).show()
                adapter.updateSongs(newListOfSongs)
            }

            btnShuffle.setOnClickListener {
                adapter.updateSongs(songManager.listOfSongs.toMutableList().shuffled())
            }

            miniPlayer.setOnClickListener {
                navigateToPlayerActivity(this@SongListActivity)
            }

            swipeToRefreshLayout.setOnRefreshListener {
                loadSongs()
                swipeToRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun loadSongs() {
        lifecycleScope.launch {
            val musicLibrary = dataRepository.getMusicLibrary()
            val newListOfSongs = musicLibrary.songs
            adapter.updateSongs(newListOfSongs)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(STATE_SELECTED_SONG, songManager.selectedSong)
        super.onSaveInstanceState(outState)
    }
    companion object {
        const val STATE_SELECTED_SONG = "selectedSong"
    }
}