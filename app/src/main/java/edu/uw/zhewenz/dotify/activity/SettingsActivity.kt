package edu.uw.zhewenz.dotify.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ericchee.songdataprovider.Song
import edu.uw.zhewenz.dotify.R
import edu.uw.zhewenz.dotify.databinding.ActivitySettingsBinding

fun launchSettingsActivity(context: Context) = with(context) {
    startActivity(Intent(this, SettingsActivity::class.java))
}

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val navController:NavController by lazy { findNavController(R.id.navHost)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater).apply { setContentView(root) }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        with(binding) {
            navController.setGraph(R.navigation.nav_graph)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return when(navController.currentDestination?.id) {
            R.id.settingsFragment -> {
                finish()
                super.onSupportNavigateUp()
            }
            else -> navController.navigateUp()
        }
    }
}