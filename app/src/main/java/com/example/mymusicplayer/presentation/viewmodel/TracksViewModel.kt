package com.example.mymusicplayer.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicplayer.data.room.db.MusicRepository
import com.example.mymusicplayer.domain.AlbumsWithTracks
import com.example.mymusicplayer.domain.MusicSourceType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TracksViewModel(private val musicApi: MusicRepository) : ViewModel() {
    private val _stateAlbumsWithTracks = MutableStateFlow<Result<AlbumsWithTracks>?>(null)
    val stateAlbumsWithTracks: StateFlow<Result<AlbumsWithTracks>?> = _stateAlbumsWithTracks

    fun loadTracks(sourceType: MusicSourceType, albumId: Long) {
        viewModelScope.launch(Dispatchers.IO) {

            val result = try {
                val value = musicApi.getTracks(sourceType, albumId)
                Result.success(value)

            } catch (t: Throwable) {
                Result.failure(t)
            }
            _stateAlbumsWithTracks.emit(result)
        }
    }
}