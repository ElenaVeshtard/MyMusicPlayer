package com.example.mymusicplayer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicplayer.data.remote.MyMusicDataSource
import com.example.mymusicplayer.domain.Tracks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyTracksViewModel(private val musicApi: MyMusicDataSource) : ViewModel() {
    private val _myStateTracks = MutableStateFlow<Result<Tracks>?>(null)
    val myStateTracks: StateFlow<Result<Tracks>?> = _myStateTracks

    fun loadTracks() {
        viewModelScope.launch(Dispatchers.IO) {

            val result = try {
                Result.success(musicApi.getTracks())

            } catch (t: Throwable) {
                Result.failure(t)
            }

            _myStateTracks.emit(result)
        }
    }
}