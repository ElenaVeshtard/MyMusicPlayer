package com.example.mymusicplayer.data.room.db

import com.example.mymusicplayer.data.room.MyDatabase
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.AlbumsWithTracks
import com.example.mymusicplayer.domain.MusicSourceType

class AlbumsRoomDataSource(database: MyDatabase) : AlbumsDbDataSource {

    private val albumsDao = database.provideDao()
    private val tracksDao = database.provideDaoTracks()


    override suspend fun saveAlbums(list: List<AlbumModel>) {
        albumsDao.saveAlbums(list)
    }

    override suspend fun getAlbums(sourceType: MusicSourceType): List<AlbumModel> {
        return albumsDao.getAlbums(sourceType.number).map { it.copy(name = it.name + "_room") }
    }

    override suspend fun getTracks(albumId: Long): AlbumsWithTracks {
        val album = albumsDao.findAlbumWithTracks(albumId)
        return if (album?.tracks != null && album.tracks.isNotEmpty())
            AlbumsWithTracks(album.album, album.tracks.map { it.copy(title = it.title + "_room") })
        else album
    }

    override suspend fun saveTracks(albumsWithTracks: AlbumsWithTracks) {
        tracksDao.saveTracks(albumsWithTracks.tracks)
    }
}