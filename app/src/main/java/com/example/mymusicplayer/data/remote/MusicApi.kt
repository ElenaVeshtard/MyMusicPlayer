package com.example.mymusicplayer.data.remote

import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.TracksModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

interface MusicApi {
    @GET("getAlbums")
    suspend fun getAlbums(): List<AlbumModel>

    @GET("getTrack")
    suspend fun getTracks(@Query("albumId") albumId: Long): TracksModel

    @Streaming
    @GET
    suspend fun downloadFile(@Url fileUrl:String): Response<ResponseBody>
}