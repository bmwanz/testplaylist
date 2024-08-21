package com.bw.testplaylist.playlist

import retrofit2.http.GET

interface PlaylistAPI {

    // set in mockoon
    @GET("playlist")
    suspend fun fetchAllPlaylists() : List<Playlist>

}