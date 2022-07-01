package com.example.mymusicplayer.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.AlbumsWithTracks
import com.example.mymusicplayer.domain.MusicSourceType

@Dao
interface AlbumsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAlbums(albums: List<AlbumModel>)

    @Query("SELECT * FROM albums where sourceType = :itunes")
    suspend fun getAlbums(itunes: Int): List<AlbumModel>

    @Query("select * from albums where id = :albumId")
    fun findAlbumById(albumId: Long): AlbumModel

    @Query("select * from albums where id = :albumId")
    suspend fun findAlbumWithTracks(albumId: Long): AlbumsWithTracks

}