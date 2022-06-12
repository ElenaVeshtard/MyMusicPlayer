package com.example.mymusicplayer.data.remote

import com.example.mymusicplayer.domain.TrackModel


interface TracksMyMusicDataSource {
    suspend fun getTracks(): List<TrackModel>
}