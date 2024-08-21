package com.bw.testplaylist.playlist

import com.bw.testplaylist.R

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int = R.mipmap.playlist
)