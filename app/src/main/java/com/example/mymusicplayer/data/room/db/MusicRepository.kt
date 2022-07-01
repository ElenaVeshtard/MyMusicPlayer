package com.example.mymusicplayer.data.room.db

import androidx.room.Transaction
import com.example.mymusicplayer.data.remote.RemoteDataSource
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.AlbumsWithTracks
import com.example.mymusicplayer.domain.MusicSourceType


class MusicRepository(
    private val albumsDbDataSource: AlbumsDbDataSource,
    private val isCacheEnabled: Boolean,
    /*private val remoteDataSource: RemoteDataSourceFake*/
    private val remoteDataSource: RemoteDataSource
) {

    @Transaction
    suspend fun getAlbums(sourceType: MusicSourceType): List<AlbumModel> {

        var cache = if (isCacheEnabled) albumsDbDataSource.getAlbums(sourceType) else null
        if (cache.isNullOrEmpty()) {
            cache = remoteDataSource.getAlbums(sourceType)
            albumsDbDataSource.saveAlbums(cache)
        }
        return cache
    }

    @Transaction
    suspend fun getTracks(sourceType: MusicSourceType, albumId: Long): AlbumsWithTracks {
        var cache = if (isCacheEnabled) albumsDbDataSource.getTracks(albumId) else null
        if (cache == null || cache.tracks.isEmpty()) {
            cache = remoteDataSource.getTracks(albumId, sourceType)
            albumsDbDataSource.saveTracks(cache)
        }
        return cache
    }
}