package com.bw.testplaylist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bw.testplaylist.model.Playlist

class PlaylistViewModel : ViewModel() {

    val playlists = MutableLiveData<Result<List<Playlist>>>()

}