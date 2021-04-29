package edu.uw.zhewenz.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.zhewenz.dotify.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private val safeArgs: SettingsFragmentArgs by navArgs()
    private val navController:NavController by lazy {findNavController()}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSettingsBinding.inflate(inflater)
        val song = safeArgs.song
        val stats = safeArgs.stats

        val myCharacter = Character(
            profilePic = R.drawable.greater_dog,
            name = "Greater Dog",
            age = 25,
            email = "greaterDog@undertale.com",
            date = "09/15/15",
            height = "?"
        )

        val myAppInfo = AppInfo(
            devName = "Zhewen Zheng",
            version = BuildConfig.VERSION_NAME,
            github = "https://github.com/QuantumEPR"
        )

        with(binding) {
            btnStats.setOnClickListener {
                navController.navigate(NavGraphDirections.actionGlobalStatisticsFragment(stats, song))
            }
            btnProfile.setOnClickListener {
                navController.navigate(NavGraphDirections.actionGlobalProfileFragment(myCharacter))
            }
            btnAbout.setOnClickListener {
                navController.navigate(NavGraphDirections.actionGlobalAboutFragment(myAppInfo))

            }
        }

        return binding.root
    }
}