package com.richardcaballero.songslist

class Song {
    var title: String? = null
    var artists: String? = null
    var coverImage: String? = null
    var songURL: String? = null

    constructor() {}
    constructor(title: String?, artists: String?, coverImage: String?, songURL: String?) {
        this.title = title
        this.artists = artists
        this.coverImage = coverImage
        this.songURL = songURL
    }
}