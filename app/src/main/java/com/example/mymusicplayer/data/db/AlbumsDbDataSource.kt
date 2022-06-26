package com.example.mymusicplayer.data.db

import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.Tracks
import retrofit2.http.Query

interface AlbumsDbDataSource {

    suspend fun saveAlbums(list: List<AlbumModel>)
    suspend fun getAlbums(): List<AlbumModel>

    suspend fun getTracks(@Query("albumId") albumId: Long): Tracks
    suspend fun saveTracks(tracks: Tracks)
}