package com.example.mymusicplayer.data.remote

import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.TracksModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class LibraryRemoteDataSourceRetrofit : RemoteDataSourceRetrofit {

        override suspend fun getAlbums(): List<AlbumModel> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://us-central1-inspiry-2ee60.cloudfunctions.net/getLibraryAlbums/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

        val musicApi: MusicApi = retrofit.create()

        return musicApi.getAlbums()
    }

    override suspend fun getTracks(albumId: Long): TracksModel {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://us-central1-inspiry-2ee60.cloudfunctions.net/getLibraryTracks/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

        val musicApi: MusicApi = retrofit.create()

        return musicApi.getTracks(1)
    }

    override suspend fun downloadFile(fileUrl: String): Response<ResponseBody> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://us-central1-inspiry-2ee60.cloudfunctions.net/getLibraryTracks/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

        val musicApi: MusicApi = retrofit.create()

        return musicApi.downloadFile(fileUrl);
    }
}