package com.bw.testplaylist.playlist

import com.bw.testplaylist.utils.BaseUnitTest
import com.bw.testplaylist.utils.captureValues
import com.bw.testplaylist.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlaylistViewModelShould : BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")

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
    fun getPlaylistFromRepository() = runTest {
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

    @Test
    fun emitErrorWhenReceiveError() {
        val viewModel = mockErrorCase()
        assertEquals(exception, viewModel.playlists.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun showSpinnerWhileLoading() = runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
           viewModel.playlists.getValueForTest()
            assertEquals(true, values[0])        // assert first value emitted by loader live data was true
        }
    }

    @Test
    fun closeLoaderAfterPlaylistsLoad() = runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    @Test
    fun closeLoaderAfterError() = runTest {
        val viewModel = mockErrorCase()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    private fun mockSuccessfulCase() : PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        return PlaylistViewModel(repository)
    }

    private fun mockErrorCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure<List<Playlist>>(exception))
                }
            )
        }
        val viewModel = PlaylistViewModel(repository)
        return viewModel
    }
}