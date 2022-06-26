package com.example.mymusicplayer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicplayer.data.db.MusicRepository
import com.example.mymusicplayer.domain.AlbumModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumsViewModel(private val musicApi: MusicRepository) : ViewModel() {

    private val _stateAlbums = MutableStateFlow<Result<List<AlbumModel>>?>(null)
    val stateAlbums: StateFlow<Result<List<AlbumModel>>?> = _stateAlbums

    fun loadAlbums() {

        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                Result.success(musicApi.getAlbums())

            } catch (t: Throwable) {
                Result.failure(t)
            }
            _stateAlbums.emit(result)
        }
    }
}
