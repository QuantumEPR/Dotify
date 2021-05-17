package edu.uw.zhewenz.dotify.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import edu.uw.zhewenz.dotify.DotifyApplication
import edu.uw.zhewenz.dotify.R
import edu.uw.zhewenz.dotify.databinding.FragmentStatisticsBinding
import edu.uw.zhewenz.dotify.manager.SongManager

class StatisticsFragment : Fragment() {
    private lateinit var binding: FragmentStatisticsBinding
    private lateinit var dotifyApp : DotifyApplication
    private lateinit var songManager : SongManager
    override fun onAttach(context: Context) {
        super.onAttach(context)
        dotifyApp = context.applicationContext as DotifyApplication
        this.songManager = dotifyApp.songManager
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticsBinding.inflate(inflater)
        val song = songManager.selectedSong
        val stats = songManager.selectedStats(song)
        with(binding) {
            if (song != null) {
                ivAlbumCover.load(song.largeImageURL)
                songDescription.text = resources.getString(R.string.song_description, song.title, song.artist)
                tvDuration.text = song.durationMillis.toString()
                tvArtist.text = song.artist
                tvSmallUrl.text = song.smallImageURL
                tvLargeUrl.text = song.largeImageURL
            }
            tvNumPlays.text = resources.getQuantityString(R.plurals.num_plays, stats, stats)


        }
        return binding.root
    }

}