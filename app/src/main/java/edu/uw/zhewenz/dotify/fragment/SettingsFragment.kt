package edu.uw.zhewenz.dotify.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import edu.uw.zhewenz.dotify.BuildConfig
import edu.uw.zhewenz.dotify.DotifyApplication
import edu.uw.zhewenz.dotify.NavGraphDirections
import edu.uw.zhewenz.dotify.R
import edu.uw.zhewenz.dotify.model.AppInfo
import edu.uw.zhewenz.dotify.model.Person
import edu.uw.zhewenz.dotify.databinding.FragmentSettingsBinding
import edu.uw.zhewenz.dotify.manager.RefreshSongManager

class SettingsFragment : Fragment() {
    private val navController:NavController by lazy {findNavController()}
    private lateinit var dotifyApp : DotifyApplication
    private val refreshSongManager: RefreshSongManager by lazy { dotifyApp.refreshSongManager }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dotifyApp = context.applicationContext as DotifyApplication
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSettingsBinding.inflate(inflater)

        with(binding) {
            btnStats.setOnClickListener {
                navController.navigate(NavGraphDirections.actionGlobalStatisticsFragment())
            }
            btnProfile.setOnClickListener {
                navController.navigate(NavGraphDirections.actionGlobalProfileFragment())
            }
            btnAbout.setOnClickListener {
                navController.navigate(NavGraphDirections.actionGlobalAboutFragment())

            }
            scSync.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    refreshSongManager.startRefreshSongsPeriodically()
                } else {
                    refreshSongManager.stopRefreshSongsPeriodically()
                }
            }
        }

        return binding.root
    }
}