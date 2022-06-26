package com.example.mymusicplayer.data.remote

import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.Tracks
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class RemoteDataSourceRetrofit : RemoteDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    private fun createRetrofit(): MusicApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://us-central1-inspiry-2ee60.cloudfunctions.net")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
        return retrofit.create()
    }

    override suspend fun getAlbums(): List<AlbumModel> {
        return createRetrofit().getAlbums()
    }

    override suspend fun getTracks(albumId: Long): Tracks {
        return createRetrofit().getTracks(1)
    }

    override suspend fun downloadFile(fileUrl: String): Response<ResponseBody> {
        TODO("Not yet implemented")
    }
}