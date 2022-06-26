package com.example.mymusicplayer.domain

import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
data class Tracks(

    @Embedded val album: AlbumModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val tracks: List<TrackModel>
)