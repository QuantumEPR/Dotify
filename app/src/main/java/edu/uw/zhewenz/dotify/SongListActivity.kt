package edu.uw.zhewenz.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.zhewenz.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongListBinding
    private lateinit var listOfSongs: List<Song>
    private var currentSong: Song? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater).apply{ setContentView(root) }


        with(binding) {
            val listOfSongs = SongDataProvider.getAllSongs()
            val adapter = SongListAdapter(listOfSongs)

            //miniPlayer.text = resources.getString(R.string.song_description, )
            rvSongs.adapter = adapter

            adapter.onSongClickListener = { song ->
                Log.i("This", song.toString())
                currentSong = song
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
                adapter.updateSongs(listOfSongs.toMutableList().shuffled())
            }

            miniPlayer.setOnClickListener {
                Log.i("This", currentSong.toString())
                navigateToPlayerActivity(this@SongListActivity, currentSong)
            }

        }


    }
}