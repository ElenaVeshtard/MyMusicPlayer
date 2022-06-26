package com.example.mymusicplayer.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.Tracks
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumsDao {

    @Insert
    suspend fun saveAlbums(albums: List<AlbumModel>)

    @Query("SELECT * FROM albums")
    suspend fun getAlbums(): List<AlbumModel>

    @Query("select * from albums where id = :albumId")
    fun findAlbumById(albumId: Long): AlbumModel

    @Query("select * from albums where id = :albumId")
    suspend fun findAlbumWithTracks(albumId: Long): Tracks

}