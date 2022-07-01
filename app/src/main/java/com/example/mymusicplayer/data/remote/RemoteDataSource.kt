package com.example.mymusicplayer.data.remote

import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.AlbumsWithTracks
import com.example.mymusicplayer.domain.MusicSourceType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Url

interface RemoteDataSource {

    suspend fun getAlbums(sourceType: MusicSourceType): List<AlbumModel>

    suspend fun getTracks(albumId: Long, sourceType: MusicSourceType): AlbumsWithTracks

    suspend fun downloadFile(fileUrl: String): Response<ResponseBody>
}