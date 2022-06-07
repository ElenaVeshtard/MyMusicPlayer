package com.example.mymusicplayer.data.libraryroom

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.TrackModel


data class LibraryTracksWithAlbumEntity(
    @Embedded val album: AlbumModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val tracks: List<TrackModel>
)