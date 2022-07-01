package com.example.mymusicplayer.data.room.db

import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.AlbumsWithTracks
import com.example.mymusicplayer.domain.MusicSourceType

interface AlbumsDbDataSource {

    suspend fun saveAlbums(list: List<AlbumModel>)
    suspend fun getAlbums(sourceType: MusicSourceType): List<AlbumModel>

    suspend fun getTracks(albumId: Long): AlbumsWithTracks
    suspend fun saveTracks(albumsWithTracks: AlbumsWithTracks)
}
