package com.example.mymusicplayer.domain

import kotlinx.serialization.Serializable

@Serializable
data class Tracks(
    val artist: String,
    val image: String,
    val title: String,
    val url: String
)