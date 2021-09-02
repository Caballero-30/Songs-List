package com.richardcaballero.songslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var songs: MutableList<Song>
    private val JSON_URL = "http://IP_Address:Port/list.json"
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.songsList)
        songs = ArrayList()
        extractSongs()
    }

    private fun extractSongs() {
        val queue = Volley.newRequestQueue(this)
        val jsonArrayRequest = JsonArrayRequest(
                Request.Method.GET, JSON_URL, null,
                {
                    for (i in 0 until it.length()) {
                        try {
                            val songObject = it.getJSONObject(i)
                            val song = Song()
                            song.title = songObject.getString("song")
                            song.artists = songObject.getString("artists")
                            song.coverImage = songObject.getString("cover_image")
                            song.songURL = songObject.getString("url")
                            songs.add(song)
                        }
                        catch (e: JSONException) { e.printStackTrace() }
                    }

                    recyclerView.layoutManager = LinearLayoutManager(this)
                    adapter = Adapter(this, songs)
                    recyclerView.adapter = adapter
                },
                { println("onErrorResponse: ${it.message}") }
        )

        queue.add(jsonArrayRequest)
    }
}