package com.bw.testplaylist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bw.testplaylist.model.PlaylistRepository
import com.bw.testplaylist.utils.MainCoroutineScopeRule
import com.bw.testplaylist.utils.getValueForTest
import com.bw.testplaylist.viewmodel.PlaylistViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlaylistViewModelShould {

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val viewModel: PlaylistViewModel
    val repository: PlaylistRepository = mock()

    init {
        viewModel = PlaylistViewModel()  // only one required to be real, everything else can be mocked

    }

    @Test
    fun getPlaylistFromRepository() {
        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }
}