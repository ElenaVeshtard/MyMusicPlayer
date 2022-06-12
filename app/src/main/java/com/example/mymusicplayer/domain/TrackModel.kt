package com.example.mymusicplayer.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "tracks")
@Serializable
class TrackModel(
    @PrimaryKey val id: Long,
    val artist: String,
    val image: String,
    val title: String,
    val url: String,
     //val albumId: Int
)