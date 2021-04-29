package edu.uw.zhewenz.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.uw.zhewenz.dotify.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {
    private val safeArgs: StatisticsFragmentArgs by navArgs()
    private lateinit var binding: FragmentStatisticsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticsBinding.inflate(inflater)
        val song = safeArgs.song
        val stats = safeArgs.stats
        with(binding) {
            ivAlbumCover.setImageResource(song.largeImageID)
            songDescription.text = resources.getString(R.string.song_description, song.title, song.artist)
            tvNumPlays.text = resources.getQuantityString(R.plurals.num_plays, stats, stats)
        }
        return binding.root
    }

}