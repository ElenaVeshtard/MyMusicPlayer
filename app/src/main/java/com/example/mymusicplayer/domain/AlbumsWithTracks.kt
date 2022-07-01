package com.example.mymusicplayer.domain

import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
data class AlbumsWithTracks(

    @Embedded val album: AlbumModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val tracks: List<TrackModel>
)