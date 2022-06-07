package com.example.mymusicplayer.data.remote

import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.TracksModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Query
import retrofit2.http.Url

interface RemoteDataSourceRetrofit {

    suspend fun getAlbums(): List<AlbumModel>

    suspend fun getTracks(@Query("albumId") albumId: Long): TracksModel

    suspend fun downloadFile(@Url fileUrl:String): Response<ResponseBody>
}