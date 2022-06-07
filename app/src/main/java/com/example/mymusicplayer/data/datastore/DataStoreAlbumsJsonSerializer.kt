package com.example.mymusicplayer.data.datastore

import androidx.datastore.core.Serializer
import com.example.mymusicplayer.domain.AlbumModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.InputStream
import java.io.OutputStream

class DataStoreAlbumsJsonSerializer(private val json: Json): Serializer<AlbumModel?> {
    override val defaultValue: AlbumModel?
        get() = null

    override suspend fun readFrom(input: InputStream): AlbumModel? {
        return json.decodeFromStream(input)
    }

    override suspend fun writeTo(t: AlbumModel?, output: OutputStream) {
        json.encodeToStream(t, output)
    }
}