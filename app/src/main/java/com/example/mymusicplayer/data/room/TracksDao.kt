package com.example.mymusicplayer.data.room

import androidx.room.Dao
import androidx.room.Insert
import com.example.mymusicplayer.domain.TrackModel

@Dao
interface TracksDao {

    @Insert
    suspend fun saveTracks(tracks: List<TrackModel>)
}