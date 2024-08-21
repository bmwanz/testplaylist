package com.bw.testplaylist.playlist

import com.bw.testplaylist.utils.BaseUnitTest
import com.bw.testplaylist.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlaylistViewModelShould : BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)

    init {
        /** getting called before rule, move to tests */
//        runBlocking {
//            whenever(repository.getPlaylists()).thenReturn(
//                flow {
//                    emit(expected)
//                }
//            )
//        }
//
//        // only object required to be real, everything else can be mocked
//        viewModel = PlaylistViewModel(repository)
    }

    @Test
    fun getPlaylistFromRepository() = runBlockingTest {
        // only object required to be real, everything else can be mocked
        val viewModel = mockSuccessfulCase()

        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository() {
        // only object required to be real, everything else can be mocked
        val viewModel = mockSuccessfulCase()

        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    private fun mockSuccessfulCase() : PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
//        val viewModel = PlaylistViewModel(repository)
//        return viewModel
        return PlaylistViewModel(repository)
    }
}