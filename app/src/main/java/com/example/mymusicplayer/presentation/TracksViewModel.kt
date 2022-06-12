package com.example.mymusicplayer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicplayer.data.remote.RemoteDataSource
import com.example.mymusicplayer.domain.Tracks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TracksViewModel(private val musicApi: RemoteDataSource) : ViewModel() {
    private val _stateITunes = MutableStateFlow<Result<Tracks>?>(null)
    val stateITunes: StateFlow<Result<Tracks>?> = _stateITunes

    fun loadTracks() {
        viewModelScope.launch(Dispatchers.IO) {

            val result = try {
                Result.success(musicApi.getTracks(1))

            } catch (t: Throwable) {
                t.printStackTrace()
                Result.failure(t)
            }

            _stateITunes.emit(result)
        }
    }
}