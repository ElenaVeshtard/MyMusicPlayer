package com.example.mymusicplayer.data.db

import com.example.mymusicplayer.data.remote.RemoteDataSourceRetrofit
import com.example.mymusicplayer.domain.AlbumModel


class AlbumsRepository(
    private val albumsDbDataSource: AlbumsDbDataSource,
    private val albumsRemoteDataSource: RemoteDataSourceRetrofit,
    private val isCacheEnabled: Boolean
) {

    suspend fun getAlbums(): List<AlbumModel> {

        var cache = if (isCacheEnabled) albumsDbDataSource.getAlbums() else null
        if (cache.isNullOrEmpty()) {
            cache = albumsRemoteDataSource.getAlbums()
            albumsDbDataSource.saveAlbums(cache)
        }

        return cache
    }
}