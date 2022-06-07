package com.example.mymusicplayer.data.db

import com.example.mymusicplayer.domain.AlbumModel

interface AlbumsDbDataSource {
    suspend fun saveAlbums(list: List<AlbumModel>)
    suspend fun getAlbums(): List<AlbumModel>?
}
