package com.richardcaballero.songslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter internal constructor(private val ctx: Context, songs: List<Song>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    lateinit var inflater: LayoutInflater
    private val songs: List<Song> = songs

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        inflater = LayoutInflater.from(ctx)
        val view: View = inflater.inflate(R.layout.custom_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind the data
        holder.songTitle.text = songs[position].title
        holder.songArtists.text = songs[position].artists
        Picasso.get().load(songs[position].coverImage).into(holder.songCoverImage)
    }

    override fun getItemCount(): Int { return songs.size }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var songTitle: TextView
        var songArtists: TextView
        var songCoverImage: ImageView

        init {
            songTitle = itemView.findViewById(R.id.songTitle)
            songArtists = itemView.findViewById(R.id.songArtist)
            songCoverImage = itemView.findViewById(R.id.coverImage)
        }
    }
}