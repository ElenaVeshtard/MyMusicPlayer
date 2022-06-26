package com.example.mymusicplayer.data.db

import androidx.room.Transaction
import com.example.mymusicplayer.data.remote.RemoteDataSourceFake
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.Tracks


class MusicRepository(
    private val albumsDbDataSource: AlbumsDbDataSource,
    private val isCacheEnabled: Boolean,
    private val remoteDataSourceFake: RemoteDataSourceFake
) {

    @Transaction
    suspend fun getAlbums(): List<AlbumModel> {

        var cache = if (isCacheEnabled) albumsDbDataSource.getAlbums() else null
        if (cache.isNullOrEmpty()) {
            cache = remoteDataSourceFake.getAlbums()
            albumsDbDataSource.saveAlbums(cache)
        }
        return cache
    }

    @Transaction
    suspend fun getTracks(): Tracks {
        var cache = if (isCacheEnabled) albumsDbDataSource.getTracks(0) else null
        if (cache == null || cache.tracks.isEmpty()) {
            cache = remoteDataSourceFake.getTracks(0)
            albumsDbDataSource.saveTracks(cache)
        }
        return cache
    }
}