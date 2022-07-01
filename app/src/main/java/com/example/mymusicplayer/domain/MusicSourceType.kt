package com.example.mymusicplayer.domain

enum class MusicSourceType(val sourceName: String, val number: Int) {
    LIBRARY("LIBRARY", 1),
    ITUNES("ITUNES", 2),
    MY_MUSIC("MY_MUSIC", 3);
}