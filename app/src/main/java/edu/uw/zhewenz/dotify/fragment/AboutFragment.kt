package edu.uw.zhewenz.dotify.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.uw.zhewenz.dotify.DotifyApplication
import edu.uw.zhewenz.dotify.R
import edu.uw.zhewenz.dotify.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private lateinit var dotifyApp : DotifyApplication
    override fun onAttach(context: Context) {
        super.onAttach(context)
        dotifyApp = context.applicationContext as DotifyApplication
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater)
        val res = resources
        val myAppInfo = dotifyApp.myAppInfo
        with(binding) {
            tvCreator.text = res.getString(R.string.creator, myAppInfo.devName)
            tvVersion.text = res.getString(R.string.version, myAppInfo.version)
            tvWebsite.text = res.getString(R.string.website, myAppInfo.github)
        }
        return binding.root
    }

}