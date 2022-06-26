package com.example.mymusicplayer.data.remote

import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.Tracks


interface MyMusicDataSource {
    suspend fun getTracks(): Tracks
    suspend fun getAlbums(): List<AlbumModel>
}