package edu.uw.zhewenz.dotify

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import edu.uw.zhewenz.dotify.databinding.ActivityPlayerBinding
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding) {
            val res = resources
            val song: Song? = intent.getParcelableExtra(SONG_KEY)
            song?.largeImageID?.let { btnAlbum.setImageResource(it) }
            tvTitle.text = song?.title
            tvArtist.text = song?.artist

            // Initialize # of plays
            numPlayed = Random.nextInt(0, 1000000)
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
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}