package com.example.mymusicplayer.data.room.model.retrofit

import com.example.mymusicplayer.domain.MusicSourceType
import kotlinx.serialization.Serializable

@Serializable
class RetrofitAlbumModelDTO(
    val id: Long,
    val image: String,
    val name: String,
    val trackCount: Int,
) {
    var sourceType: MusicSourceType = MusicSourceType.ITUNES
}