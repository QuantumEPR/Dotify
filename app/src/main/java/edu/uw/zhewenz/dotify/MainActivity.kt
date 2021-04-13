package edu.uw.zhewenz.dotify

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
//import edu.uw.zhewenz.dotify.databinding.ActivityMainBinding
 import edu.uw.zhewenz.dotify.databinding.ActivityMainLinearBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainLinearBinding
    private var numPlayed: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainLinearBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        with(binding) {
            val res = resources
            // Change User Button
            btnChangeUser.setOnClickListener {
                if (etUser.isInvisible) {
                    btnChangeUser.text = getString(R.string.apply)
                    etUser.visibility = View.VISIBLE
                    tvUser.visibility = View.GONE
                } else {
                    val username = etUser.text.toString()
                    if (TextUtils.isEmpty(username)){
                        etUser.error = getString(R.string.empty_error_msg, "Username")
                    } else {
                        btnChangeUser.setText(R.string.change_user)
                        tvUser.text = etUser.text
                        etUser.visibility = View.INVISIBLE
                        tvUser.visibility = View.VISIBLE
                    }
                }
            }

            // Initialize # of plays
            numPlayed = Random.nextInt(0, 1000000)
            tvNumPlays.text = res.getQuantityString(R.plurals.num_plays, numPlayed, numPlayed)

            // Prev Button
            btnPrev.setOnClickListener {
                Toast.makeText(this@MainActivity, "Skipping to previous track", Toast.LENGTH_SHORT).show()
            }

            // Play Button
            btnPlay.setOnClickListener {
                numPlayed += 1
                tvNumPlays.text = res.getQuantityString(R.plurals.num_plays, numPlayed, numPlayed)
            }

            // Next Button
            btnNext.setOnClickListener {
                Toast.makeText(this@MainActivity, "Skipping to next track", Toast.LENGTH_SHORT).show()
            }
            // Album Button
            btnAlbum.setOnLongClickListener {
                val color = Color.argb(255, Random.nextInt(256),Random.nextInt(256),Random.nextInt(256))
                tvNumPlays.setTextColor(color)
                true
            }
        }
    }
}