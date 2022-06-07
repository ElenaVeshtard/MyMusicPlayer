package com.example.mymusicplayer.data.itunesroom

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.TrackModel


data class ITunesTracksWithAlbumEntity(
    @Embedded val album: AlbumModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val tracks: List<TrackModel>
)