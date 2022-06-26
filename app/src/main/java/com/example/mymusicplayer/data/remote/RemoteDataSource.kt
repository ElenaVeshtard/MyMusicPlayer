package com.example.mymusicplayer.data.remote

import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.Tracks
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Query
import retrofit2.http.Url

interface RemoteDataSource {

    suspend fun getAlbums(): List<AlbumModel>

    suspend fun getTracks(albumId: Long): Tracks

    suspend fun downloadFile(fileUrl: String): Response<ResponseBody>
}