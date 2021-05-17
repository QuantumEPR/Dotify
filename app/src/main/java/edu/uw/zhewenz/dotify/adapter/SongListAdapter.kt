package edu.uw.zhewenz.dotify.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import edu.uw.zhewenz.dotify.DotifyApplication
import edu.uw.zhewenz.dotify.model.Song
import edu.uw.zhewenz.dotify.SongDiffCallback
import edu.uw.zhewenz.dotify.databinding.ItemSongBinding
import edu.uw.zhewenz.dotify.manager.SongManager
import kotlin.random.Random


class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongListViewHolder>() {
    var onSongClickListener: (song: Song) -> Unit ={ _-> }
    var onSongLongClickListener: (position: Int, song: Song) -> Unit ={ _, _ -> }
    lateinit var dotifyApp: DotifyApplication
    lateinit var songManager: SongManager
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        dotifyApp = recyclerView.context.applicationContext as DotifyApplication
        songManager = dotifyApp.songManager
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongListViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))
        return SongListViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfSongs.size


    override fun onBindViewHolder(holder: SongListViewHolder, position: Int) {
        val song = listOfSongs[position]
        with(holder.binding) {
            tvSongName.text = song.title
            tvArtistName.text = song.artist
            ivAlbumCover.load(song.smallImageURL)
            root.setOnClickListener {
                onSongClickListener(song)
            }
        }
    }

    fun updateSongs(newListOfSongs: List<Song>) {
        val callback = SongDiffCallback(newListOfSongs, listOfSongs)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)
        songManager.updateSongs(newListOfSongs)
        this.listOfSongs = newListOfSongs
    }
    class SongListViewHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)
}
