package edu.uw.zhewenz.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ericchee.songdataprovider.Song
import edu.uw.zhewenz.dotify.databinding.ActivitySettingsBinding

private const val SONG_KEY = "song"

fun launchSettingsActivity(context: Context, song: Song) = with(context) {
    startActivity(Intent(this, SettingsActivity::class.java).apply {
        putExtra(SONG_KEY, song)
    })
}

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val navController:NavController by lazy { findNavController(R.id.navHost)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater).apply { setContentView(root) }
        with(binding) {
            navController.setGraph(R.navigation.nav_graph, intent.extras)
        }
    }
}