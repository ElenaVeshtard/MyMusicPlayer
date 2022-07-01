package com.example.mymusicplayer.data.remote

import com.example.mymusicplayer.data.room.model.retrofit.RetrofitAlbumModelDTO
import com.example.mymusicplayer.data.room.model.retrofit.RetrofitAlbumsWithTracksDTO
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

interface MusicApi {
    @GET("https://stellio.ru/.inspiry/api/?p=getItunesAlbums")
    suspend fun getItunesAlbums(): List<RetrofitAlbumModelDTO>

    @GET("https://stellio.ru/.inspiry/api/?p=getLibraryAlbums")
    suspend fun getLibraryAlbums(): List<RetrofitAlbumModelDTO>

    @GET("https://stellio.ru/.inspiry/api/?p=getItunesTracks")
    suspend fun getItunesTracks(@Query("albumId") albumId: Long): RetrofitAlbumsWithTracksDTO

    @GET("https://stellio.ru/.inspiry/api/?p=getLibraryTracks")
    suspend fun getLibraryTracks(@Query("albumId") albumId: Long): RetrofitAlbumsWithTracksDTO

    @Streaming
    @GET
    suspend fun downloadFile(@Url fileUrl: String): Response<ResponseBody>
}