package com.example.mymusicplayer.data.itunesroom

import androidx.room.Dao
import androidx.room.Query
import com.example.mymusicplayer.domain.TrackModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TracksDao {

    @Query("select * from tracks where albumId = :albumId")
    fun getTracksByAlbumId(albumId: Long): Flow<List<TrackModel>>
}