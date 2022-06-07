package com.example.mymusicplayer.data.libraryroom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.TrackModel
import com.github.krottv.tmstemp.data.libraryroom.LibraryTracksDao

@Database(entities = [AlbumModel::class, TrackModel::class], version = 1)
abstract class LibraryDatabase: RoomDatabase() {

    abstract fun provideDao(): LibraryAlbumsDao
    abstract fun provideDaoTracks(): LibraryTracksDao
}