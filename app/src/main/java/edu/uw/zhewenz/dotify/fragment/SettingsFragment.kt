package edu.uw.zhewenz.dotify.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import edu.uw.zhewenz.dotify.BuildConfig
import edu.uw.zhewenz.dotify.NavGraphDirections
import edu.uw.zhewenz.dotify.R
import edu.uw.zhewenz.dotify.model.AppInfo
import edu.uw.zhewenz.dotify.model.Person
import edu.uw.zhewenz.dotify.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private val navController:NavController by lazy {findNavController()}
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
        }

        return binding.root
    }
}