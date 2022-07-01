package com.example.mymusicplayer.data.room.model.mapper

import com.example.mymusicplayer.domain.AlbumsWithTracks
import com.example.mymusicplayer.data.room.model.retrofit.RetrofitAlbumsWithTracksDTO

class AlbumsWithTracksMapper : Mapper<RetrofitAlbumsWithTracksDTO, AlbumsWithTracks> {

    override fun map(from: RetrofitAlbumsWithTracksDTO): AlbumsWithTracks {
        return AlbumsWithTracks(AlbumsMapper().map(from.album), from.tracks.map { track ->
            val mappedTrack = TracksMapper().map(track)
            mappedTrack.albumId = from.album.id
            mappedTrack
        })
    }
}