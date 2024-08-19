package com.bw.testplaylist.model

import com.bw.testplaylist.R

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int = R.mipmap.playlist
)