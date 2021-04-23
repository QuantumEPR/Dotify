package edu.uw.zhewenz.dotify

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import edu.uw.zhewenz.dotify.databinding.ItemSongBinding


class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongListViewHolder>() {
    var onSongClickListener: (song: Song) -> Unit ={ _-> }
    var onSongLongClickListener: (position: Int, song: Song) -> Unit ={ _, _ -> }
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
            ivAlbumCover.setImageResource(song.smallImageID)
            root.setOnClickListener {
                onSongClickListener(song)
            }
        }
    }

    fun updateSongs(newListOfSongs: List<Song>) {
        Log.i("new", newListOfSongs.toString())
        Log.i("old", listOfSongs.toString())
        val callback = SongDiffCallback(newListOfSongs, listOfSongs)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)

        this.listOfSongs = newListOfSongs
//        notifyDataSetChanged()
    }
    class SongListViewHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)
}
