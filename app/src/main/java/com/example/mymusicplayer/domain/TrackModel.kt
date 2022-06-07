package com.example.mymusicplayer.domain

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tracks")
class TrackModel(
    @PrimaryKey val id: Long,
    val title: String,
    val subtitle: String,
    val image: String,
    val albumId: Long
)