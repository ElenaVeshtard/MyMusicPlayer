package com.example.mymusicplayer.data.room.model.mapper

import com.example.mymusicplayer.domain.TrackModel
import com.example.mymusicplayer.data.room.model.retrofit.RetrofitTrackModelDTO

class TracksMapper : Mapper<RetrofitTrackModelDTO, TrackModel> {
    override fun map(from: RetrofitTrackModelDTO): TrackModel {
        return TrackModel(from.artist, from.image, from.title, from.url, from.sourceType.number)
    }
}
