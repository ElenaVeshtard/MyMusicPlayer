package com.example.mymusicplayer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicplayer.data.db.AlbumsRepository
import com.example.mymusicplayer.domain.AlbumModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AlbumsViewModel(private val musicApi: AlbumsRepository) : ViewModel() {

    private val _stateITunes = MutableStateFlow<Result<List<AlbumModel>>?>(null)
    val stateITunes: StateFlow<Result<List<AlbumModel>>?> = _stateITunes

    fun loadData() {

        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                Result.success(musicApi.getAlbums())

            } catch (t: Throwable) {
                t.printStackTrace()
                Result.failure(t)
            }

            _stateITunes.emit(result)
        }
    }
}
