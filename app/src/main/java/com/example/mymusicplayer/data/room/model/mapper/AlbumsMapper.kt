package com.example.mymusicplayer.data.room.model.mapper

import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.data.room.model.retrofit.RetrofitAlbumModelDTO

class AlbumsMapper : Mapper<RetrofitAlbumModelDTO, AlbumModel> {
    override fun map(from: RetrofitAlbumModelDTO): AlbumModel {
        return AlbumModel(from.id, from.image, from.name, from.trackCount, from.sourceType.number)
    }
}