package com.example.mymusicplayer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicplayer.data.db.MusicRepository
import com.example.mymusicplayer.domain.Tracks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TracksViewModel(private val musicApi: MusicRepository) : ViewModel() {
    private val _stateTracks = MutableStateFlow<Result<Tracks>?>(null)
    val stateTracks: StateFlow<Result<Tracks>?> = _stateTracks

    fun loadTracks() {
        viewModelScope.launch(Dispatchers.IO) {

            val result = try {
                Result.success(musicApi.getTracks())

            } catch (t: Throwable) {
                Result.failure(t)
            }
            _stateTracks.emit(result)
        }
    }
}