package com.example.mymusicplayer.data.remote

import android.content.ContentUris.withAppendedId
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.TrackModel
import com.example.mymusicplayer.domain.Tracks

class MyMusicDataSourceImpl(private val context: Context) :
    MyMusicDataSource {

    private val contentUriAlbums: Uri by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Audio.Albums.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
        }
    }
    private val contentUriTracks: Uri by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Audio.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        }
    }

    override suspend fun getTracks(): Tracks {

        val contentResolver = context.contentResolver!!

        val cursor = contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Albums.ALBUM_ID
            ),
            null,
            null,
            MediaStore.Audio.Media.ALBUM + " asc"
        )

        val result = mutableListOf<TrackModel>()
        if (cursor?.moveToFirst() == true) {

            do {
                val artist = cursor.getString(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                )
                val id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                )
                val title = cursor.getString(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                )
                val albumId = cursor.getString(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ID)
                )
                val image = withAppendedId(contentUriTracks, id)

                result.add(TrackModel(id, artist, image.toString(), title, "", albumId.toLong()))

            } while (cursor.moveToNext())
            cursor.close()
        }
        return Tracks(getAlbums()[0], result)
    }

    override suspend fun getAlbums(): List<AlbumModel> {

        val contentResolver = context.contentResolver!!

        val cursor = contentResolver.query(
            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Audio.Albums.ARTIST,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ALBUM_ID
            ),
            null,
            null,
            MediaStore.Audio.Albums.ALBUM + " asc"
        )

        val result = mutableListOf<AlbumModel>()
        if (cursor?.moveToFirst() == true) {

            do {
                val id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ID)
                )
                val title = cursor.getString(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM)
                )
                val image = withAppendedId(contentUriAlbums, id)
                result.add(AlbumModel(id, image.toString(), title, 5))

            } while (cursor.moveToNext())
            cursor.close()
        }
        return result
    }
}
