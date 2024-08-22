package com.bw.testplaylist.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData

class PlaylistViewModel(
    private val repository: PlaylistRepository
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val playlists = liveData<Result<List<Playlist>>> {
        loader.postValue(true)
        emitSource(repository.getPlaylists().asLiveData())
    }
}