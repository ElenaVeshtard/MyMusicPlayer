package com.example.mymusicplayer.data.remote

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.example.mymusicplayer.domain.AlbumModel

/*
class AlbumsMyMusicDataSource(private val context: Context) :
    AlbumRemoteDataSource {

    private val contentUriAlbums: Uri by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Audio.Albums.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
        }
    }

    override suspend fun getAlbums(): List<AlbumModel> {

        val contentResolver = context.contentResolver!!

        val cursor = contentResolver.query(
            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.NUMBER_OF_SONGS,
                MediaStore.Audio.Albums.ALBUM
            ),
            null,
            null,
            MediaStore.Audio.Albums.ALBUM + " asc"
        )

        val result = mutableListOf<AlbumModel>()
        if (cursor?.moveToFirst() == true) {

            do {
                val album = cursor.getString(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM)
                )
                val id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums._ID)
                )
                val numberOfSongs = cursor.getInt(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.NUMBER_OF_SONGS)
                )

                val image = ContentUris
                    .withAppendedId(contentUriAlbums, id)

                result.add(AlbumModel(id, image.toString(), album, numberOfSongs))

            } while (cursor.moveToNext())
        }
        return result
    }
}*/
