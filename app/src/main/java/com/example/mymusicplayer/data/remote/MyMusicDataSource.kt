package com.example.mymusicplayer.data.remote

import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.AlbumsWithTracks


interface MyMusicDataSource {
    suspend fun getTracks(): AlbumsWithTracks
    suspend fun getAlbums(): List<AlbumModel>
}