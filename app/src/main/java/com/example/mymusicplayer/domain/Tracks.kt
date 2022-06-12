package com.example.mymusicplayer.domain

import kotlinx.serialization.Serializable

@Serializable
data class Tracks(
    val album: AlbumModel,
    val tracks: ArrayList<TrackModel>
)