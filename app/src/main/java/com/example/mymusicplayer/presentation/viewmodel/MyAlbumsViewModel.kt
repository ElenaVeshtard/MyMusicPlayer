package com.example.mymusicplayer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicplayer.data.remote.MyMusicDataSource
import com.example.mymusicplayer.domain.AlbumModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyAlbumsViewModel(private val musicApi: MyMusicDataSource) : ViewModel() {

    private val _stateMyAlbums = MutableStateFlow<Result<List<AlbumModel>>?>(null)
    val stateMyAlbums: StateFlow<Result<List<AlbumModel>>?> = _stateMyAlbums

    fun loadAlbums() {

        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                Result.success(musicApi.getAlbums())

            } catch (t: Throwable) {
                Result.failure(t)
            }
            _stateMyAlbums.emit(result)
        }
    }
}
