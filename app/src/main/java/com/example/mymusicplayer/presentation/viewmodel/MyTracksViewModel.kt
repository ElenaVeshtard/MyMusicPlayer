package com.example.mymusicplayer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicplayer.data.remote.MyMusicDataSource
import com.example.mymusicplayer.domain.AlbumsWithTracks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyTracksViewModel(private val musicApi: MyMusicDataSource) : ViewModel() {
    private val _myStateAlbumsWithTracks = MutableStateFlow<Result<AlbumsWithTracks>?>(null)
    val myStateAlbumsWithTracks: StateFlow<Result<AlbumsWithTracks>?> = _myStateAlbumsWithTracks

    fun loadTracks() {
        viewModelScope.launch(Dispatchers.IO) {

            val result = try {
                Result.success(musicApi.getTracks())

            } catch (t: Throwable) {
                Result.failure(t)
            }

            _myStateAlbumsWithTracks.emit(result)
        }
    }
}