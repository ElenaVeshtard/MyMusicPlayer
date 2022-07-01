package com.example.mymusicplayer.data.room.model.retrofit

import com.example.mymusicplayer.domain.MusicSourceType
import kotlinx.serialization.Serializable

@Serializable

class RetrofitTrackModelDTO(
    val artist: String,
    val image: String,
    val title: String,
    val url: String,
) {
    var sourceType: MusicSourceType = MusicSourceType.ITUNES

}