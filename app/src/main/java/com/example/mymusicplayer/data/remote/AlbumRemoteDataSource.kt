package com.example.mymusicplayer.data.remote

import com.example.mymusicplayer.domain.AlbumModel


interface AlbumRemoteDataSource {
    suspend fun getAlbums(): List<AlbumModel>
}