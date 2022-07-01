package com.example.mymusicplayer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicplayer.data.room.db.MusicRepository
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.MusicSourceType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumsViewModel(private val musicApi: MusicRepository) : ViewModel() {

    private val _stateAlbums = MutableStateFlow<Result<List<AlbumModel>>?>(null)
    val stateAlbums: StateFlow<Result<List<AlbumModel>>?> = _stateAlbums

    fun loadAlbums(sourceType: MusicSourceType) {

        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                Result.success(musicApi.getAlbums(sourceType))

            } catch (t: Throwable) {
                Result.failure(t)
            }
            _stateAlbums.emit(result)
        }
    }
}
