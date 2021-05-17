package edu.uw.zhewenz.dotify.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import coil.load
import com.ericchee.songdataprovider.Song
import edu.uw.zhewenz.dotify.DotifyApplication
import edu.uw.zhewenz.dotify.R
import edu.uw.zhewenz.dotify.databinding.ActivityPlayerBinding
import edu.uw.zhewenz.dotify.activity.launchSettingsActivity
import edu.uw.zhewenz.dotify.manager.SongManager
import kotlin.random.Random

// For Extra Credit
// import edu.uw.zhewenz.dotify.databinding.ActivityMainLinearBinding

fun navigateToPlayerActivity(context: Context) = with(context) {
    val intent = Intent(this, PlayerActivity::class.java)
    startActivity(intent)
}


class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    private lateinit var dotifyApp: DotifyApplication
    private lateinit var songManager: SongManager

    private var numPlayed: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dotifyApp = this.applicationContext as DotifyApplication
        songManager = dotifyApp.songManager
        binding = ActivityPlayerBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding) {

            val res = resources
            val song = songManager.selectedSong
            if (savedInstanceState != null) {
                with(savedInstanceState) {
                    numPlayed = getInt(STATE_NUM_PLAYED)
                }
            } else {
                songManager.mapOfSongs[song].let {
                    if (it != null) {
                        numPlayed = it
                    }
                }
            }

            song?.largeImageURL?.let { btnAlbum.load(it) }
            tvTitle.text = song?.title
            tvArtist.text = song?.artist

            // # of plays
            tvNumPlays.text = res.getQuantityString(R.plurals.num_plays, numPlayed, numPlayed)

            // Prev Button
            btnPrev.setOnClickListener {
                Toast.makeText(this@PlayerActivity, "Skipping to previous track", Toast.LENGTH_SHORT).show()
            }

            // Play Button
            btnPlay.setOnClickListener {
                numPlayed += 1
                songManager.updateStats(song, numPlayed)
                tvNumPlays.text = res.getQuantityString(R.plurals.num_plays, numPlayed, numPlayed)
            }

            // Next Button
            btnNext.setOnClickListener {
                Toast.makeText(this@PlayerActivity, "Skipping to next track", Toast.LENGTH_SHORT).show()
            }
            // Album Button
            btnAlbum.setOnLongClickListener {
                val color = Color.argb(255, Random.nextInt(256),Random.nextInt(256),Random.nextInt(256))
                tvNumPlays.setTextColor(color)
                true
            }

            // Settings Button
            btnSettings.setOnClickListener {
                song?.let{ launchSettingsActivity(this@PlayerActivity) }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("InstanceState", "saving")
        outState.putInt(STATE_NUM_PLAYED, numPlayed )

        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemSettings -> launchSettingsActivity(this@PlayerActivity)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val STATE_NUM_PLAYED = "numPlayed"
    }
}


