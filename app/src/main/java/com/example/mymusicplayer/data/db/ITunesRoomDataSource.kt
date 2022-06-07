package com.example.mymusicplayer.data.db

import com.example.mymusicplayer.data.itunesroom.MyDatabase
import com.example.mymusicplayer.domain.AlbumModel

class ITunesRoomDataSource(database: MyDatabase): AlbumsDbDataSource {

    private val dao = database.provideDao()

    override suspend fun saveAlbums(list: List<AlbumModel>) {
        dao.saveAlbums(list)
    }

    override suspend fun getAlbums(): List<AlbumModel> {
        return dao.getAlbums().map { it.copy(name = it.name + "_iTunes") }
    }
}