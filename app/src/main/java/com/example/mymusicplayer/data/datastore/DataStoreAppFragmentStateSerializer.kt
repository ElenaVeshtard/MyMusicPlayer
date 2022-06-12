package com.example.mymusicplayer.data.datastore

import androidx.datastore.core.Serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.InputStream
import java.io.OutputStream

class DataStoreAppFragmentStateSerializer(private val json: Json) : Serializer<AppFragmentState> {
    override val defaultValue: AppFragmentState
        get() = AppFragmentState(numberOfFragment = 0)

    override suspend fun readFrom(input: InputStream): AppFragmentState {
        return json.decodeFromStream(input)
    }

    override suspend fun writeTo(t: AppFragmentState, output: OutputStream) {
        json.encodeToStream(t, output)
    }
}