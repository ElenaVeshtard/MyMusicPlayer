package com.example.mymusicplayer.data.room.model.mapper

interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}
