package com.example.mymusicplayer.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "tracks")
@Serializable
data class TrackModel(
    val artist: String,
    val image: String,
    val title: String,
    val url: String,
    var sourceType: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0;
    var albumId: Long = 0

    constructor(
        id: Long,
        artist: String,
        image: String,
        title: String,
        url: String,
        albumId: Long,
        sourceType: Int
    ) : this(artist, image, title, url, sourceType) {
        this.id = id
        this.albumId = albumId
    }
}