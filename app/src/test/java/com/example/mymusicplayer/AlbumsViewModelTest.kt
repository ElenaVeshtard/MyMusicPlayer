package com.example.mymusicplayer

import com.example.mymusicplayer.data.db.MusicRepository
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.presentation.AlbumsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.runTest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AlbumsViewModelTest {

    @Test
    fun `test exception handling in viewModel`() {
        try {
            runTest(dispatchTimeoutMs = 1000L) {
                val repository: MusicRepository = mockk()

                coEvery { repository.getAlbums() } throws IllegalAccessException("test error")
                val viewModel = AlbumsViewModel(repository)

                viewModel.loadAlbums()

                viewModel.stateAlbums.collect {
                    if (it != null) {
                        Assertions.assertTrue(it.isFailure)
                        cancel()
                    }
                }
            }
        } catch (e: Exception) {
        }
    }

    @Test
    fun `test normal response in viewModel`() {
        try {
            runTest(dispatchTimeoutMs = 1000L) {
                val repository: MusicRepository = mockk()

                coEvery { repository.getAlbums() } returns listOf(AlbumModel(0,"image", "name", 5))
                val viewModel = AlbumsViewModel(repository)

                viewModel.loadAlbums()

                viewModel.stateAlbums.collect {
                    if (it != null) {
                        Assertions.assertEquals(listOf(AlbumModel(0,"image", "name", 5)), it.getOrThrow())
                        cancel()
                    }
                }
            }
        } catch (e: Exception) {
        }
    }
}