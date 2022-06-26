package com.example.mymusicplayer.data.db

import com.example.mymusicplayer.data.room.MyDatabase
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.Tracks

class AlbumsRoomDataSource(database: MyDatabase) : AlbumsDbDataSource {

    private val albumsDao = database.provideDao()
    private val tracksDao = database.provideDaoTracks()


    override suspend fun saveAlbums(list: List<AlbumModel>) {
        albumsDao.saveAlbums(list)
    }

    override suspend fun getAlbums(): List<AlbumModel> {
        return albumsDao.getAlbums().map { it.copy(name = it.name + "_room") }
    }

    override suspend fun getTracks(albumId: Long): Tracks {
        val album = albumsDao.findAlbumWithTracks(albumId)
        return if (album?.tracks != null && album.tracks.isNotEmpty())
            Tracks(album.album, album.tracks.map { it.copy(title = it.title + "_room") })
        else album
    }

    override suspend fun saveTracks(tracks: Tracks) {
        tracksDao.saveTracks(tracks.tracks)
    }
}