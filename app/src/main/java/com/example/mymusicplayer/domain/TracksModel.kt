package com.example.mymusicplayer.domain

import kotlinx.serialization.Serializable

@Serializable
data class TracksModel(
    val album: Album,
    val tracks: List<Tracks>
)