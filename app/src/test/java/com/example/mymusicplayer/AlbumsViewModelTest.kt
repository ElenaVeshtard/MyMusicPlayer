package com.example.mymusicplayer

import com.example.mymusicplayer.data.remote.RemoteDataSource
import com.example.mymusicplayer.presentation.AlbumsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions

class AlbumsViewModelTest {

    @Test
    fun `test exeption hendling in viewModel`() = runTest {
        val repository: RemoteDataSource = mockk()

        coEvery { repository.getAlbums(false) } throws IllegalAccessException("test error")
        val viewModel = AlbumsViewModel(repository)

        viewModel.loadData(false)

        viewModel.stateITunes.collect {
            if (it != null){
                Assertions.assertTrue(it.isFailure)
            }
        }
    }

    @Test
    fun `test normal response in viewModel`() {

    }
}