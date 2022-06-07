package com.example.mymusicplayer.data.purchase

interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}
