package com.example.mymusicplayer.data.room.model.retrofit

import kotlinx.serialization.Serializable

@Serializable
class RetrofitAlbumsWithTracksDTO(
    val album: RetrofitAlbumModelDTO,
    val tracks: List<RetrofitTrackModelDTO>
)