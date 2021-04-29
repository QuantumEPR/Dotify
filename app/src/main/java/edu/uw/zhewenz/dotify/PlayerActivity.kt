package edu.uw.zhewenz.dotify

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import edu.uw.zhewenz.dotify.databinding.ActivityPlayerBinding
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

// For Extra Credit
// import edu.uw.zhewenz.dotify.databinding.ActivityMainLinearBinding

private const val SONG_KEY = "song"

fun navigateToPlayerActivity(context: Context, song: Song?) = with(context) {
    var intent = Intent(this, PlayerActivity::class.java).apply {
        val bundle = Bundle().apply {
            putParcelable(SONG_KEY, song)
        }
        putExtras(bundle)
        // Alternative
        // putExtra(SONG_KEY, song)
    }

    startActivity(intent)
}


class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    private var numPlayed: Int = 0
    private var song: Song? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding) {
            if (savedInstanceState != null) {
                with(savedInstanceState) {
                    numPlayed = getInt(STATE_NUM_PLAYED)
                }
            } else {
                Log.i("InstanceState", "unavailable")
                numPlayed = Random.nextInt(0, 1000000)
            }
            val res = resources
            song = intent.getParcelableExtra(SONG_KEY)
            song?.largeImageID?.let { btnAlbum.setImageResource(it) }
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
                song?.let{ launchSettingsActivity(this@PlayerActivity, it, numPlayed) }
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
            R.id.itemSettings -> song?.let{ launchSettingsActivity(this@PlayerActivity, it, numPlayed) }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val STATE_NUM_PLAYED = "numPlayed"
    }
}

@Parcelize
data class Character (
    val profilePic: Int,
    val name: String,
    val age: Int,
    val email: String,
    val date: String,
    val height: String
): Parcelable

@Parcelize
data class AppInfo (
    val devName: String,
    val version: String,
    val github: String
): Parcelable