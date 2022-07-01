package com.example.mymusicplayer.data.remote

import com.example.mymusicplayer.data.room.model.mapper.AlbumsMapper
import com.example.mymusicplayer.data.room.model.mapper.AlbumsWithTracksMapper
import com.example.mymusicplayer.data.room.model.retrofit.RetrofitAlbumModelDTO
import com.example.mymusicplayer.data.room.model.retrofit.RetrofitAlbumsWithTracksDTO
import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.AlbumsWithTracks
import com.example.mymusicplayer.domain.MusicSourceType
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
            .baseUrl("https://stellio.ru")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
        return retrofit.create()
    }

    override suspend fun getAlbums(sourceType: MusicSourceType): List<AlbumModel> {
        val retrofitAlbums: List<RetrofitAlbumModelDTO> = when (sourceType) {
            MusicSourceType.ITUNES -> createRetrofit().getItunesAlbums()
            MusicSourceType.LIBRARY -> createRetrofit().getLibraryAlbums()
            else -> {
                createRetrofit().getItunesAlbums()
            }
        }
        return retrofitAlbums.map { albums ->
            albums.sourceType = sourceType
            AlbumsMapper().map(albums)
        }
    }

    override suspend fun getTracks(albumId: Long, sourceType: MusicSourceType): AlbumsWithTracks {
        val retrofitTracks: RetrofitAlbumsWithTracksDTO = when (sourceType) {
            MusicSourceType.ITUNES -> createRetrofit().getItunesTracks(albumId)
            MusicSourceType.LIBRARY -> createRetrofit().getLibraryTracks(albumId)
            else -> {
                createRetrofit().getItunesTracks(albumId)
            }
        }
        val mappedTracks = retrofitTracks.tracks.map { tracks ->
            tracks.sourceType = sourceType
            tracks
        }
        return AlbumsWithTracksMapper().map(
            RetrofitAlbumsWithTracksDTO(
                retrofitTracks.album,
                mappedTracks
            )
        )
    }

    override suspend fun downloadFile(fileUrl: String): Response<ResponseBody> {
        return createRetrofit().downloadFile(fileUrl)
    }
}