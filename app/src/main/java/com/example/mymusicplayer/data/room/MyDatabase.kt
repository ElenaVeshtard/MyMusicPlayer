package com.example.mymusicplayer.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.TrackModel


@Database(entities = [AlbumModel::class, TrackModel::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun provideDao(): AlbumsDao
    abstract fun provideDaoTracks(): TracksDao
}