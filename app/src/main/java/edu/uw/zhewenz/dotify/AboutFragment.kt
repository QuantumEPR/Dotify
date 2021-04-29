package edu.uw.zhewenz.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.uw.zhewenz.dotify.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private val safeArgs: AboutFragmentArgs by navArgs()
    private lateinit var binding: FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater)
        val res = resources
        val myAppInfo = safeArgs.appInfo
        with(binding) {
            tvCreator.text = res.getString(R.string.creator, myAppInfo.devName)
            tvVersion.text = res.getString(R.string.version, myAppInfo.version)
            tvWebsite.text = res.getString(R.string.website, myAppInfo.github)
        }
        return binding.root
    }

}