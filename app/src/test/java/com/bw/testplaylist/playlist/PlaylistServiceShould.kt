package com.bw.testplaylist.playlist

import com.bw.testplaylist.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class PlaylistServiceShould : BaseUnitTest() {

    private lateinit var service: PlaylistService
    private val api: PlaylistAPI = mock()
    private val playlists: List<Playlist> = mock()

    @Test
    fun fetchPlaylistsFromAPI() = runTest {
        service = PlaylistService(api)
        service.fetchPlaylists().first()
        verify(api, times(1)).fetchAllPlaylists()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem() = runTest {
        mockSuccessfulCase()
        assertEquals(Result.success(playlists), service.fetchPlaylists().first())
    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runTest {
        mockFailureCase()
        assertEquals("Something went wrong",
            service.fetchPlaylists().first().exceptionOrNull()?.message)
    }

    private suspend fun mockFailureCase() {
        whenever(api.fetchAllPlaylists())
            .thenThrow(RuntimeException("Damn backend developers!"))

        service = PlaylistService(api)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchAllPlaylists()).thenReturn(playlists)
        service = PlaylistService(api)
    }
}