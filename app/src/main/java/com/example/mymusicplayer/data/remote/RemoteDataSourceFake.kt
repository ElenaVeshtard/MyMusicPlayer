package com.example.mymusicplayer.data.remote

import com.example.mymusicplayer.domain.AlbumModel
import com.example.mymusicplayer.domain.TrackModel
import com.example.mymusicplayer.domain.Tracks
import okhttp3.ResponseBody
import retrofit2.Response

class RemoteDataSourceFake : RemoteDataSource {

    override suspend fun getAlbums(): List<AlbumModel> {
        return arrayListOf(
            AlbumModel(
                0,
                "https://images.unsplash.com/photo-1568127861543-b0c0696c735f?ixlib" +
                        "=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80",
                "USA",
                1
            ),
            AlbumModel(
                1,
                "https://i.ytimg.com/vi/K3HQeCCHCns/hqdefault.jpg",
                "States",
                1
            ),
            AlbumModel(
                2,
                "https://www.artwall.ru/files/products/400_F_44702574.jpg",
                "Animal",
                1
            ),
            AlbumModel(
                3,
                "https://avatars.mds.yandex.net/i?id=35723849c3056ca9fee5e3af89027eb0-7013372-images-thumbs&n=13",
                "Belarus",
                1
            ),
            AlbumModel(
                4,
                "https://inspiry-2ee60.web.app/music/images/itunes/hip_hop.jpg",
                "Stars",
                1
            )
        )
    }

    override suspend fun getTracks(albumId: Long): Tracks {

        return Tracks(
            AlbumModel(
                0,
                "https://images.unsplash.com/photo-1568127861543-b0c0696c735f?ixlib" +
                        "=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80",
                "Some Text",
                1,
            ),
            arrayListOf(
                TrackModel(
                    0,
                    "California",
                    "https://inspiry-2ee60.web.app/music/images/itunes/hip_hop.jpg",
                    "California",
                    "https://muzati.net/music/0-0-1-18352-20",
                    0
                ),
                TrackModel(
                    1,
                    "Washington",
                    "https://images.unsplash.com/photo-1568127861543-b0c0696c735f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80",
                    "Washington",
                    "https://muzati.net/music/0-0-1-18352-21",
                    0
                ),
                TrackModel(
                    2,
                    "Nevada",
                    "https://images.unsplash.com/photo-1563452965085-2e77e5bf2607?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80",
                    "Nevada",
                    "https://muzati.net/music/0-0-1-18352-21",
                    0
                ),
                TrackModel(
                    3,
                    "Sky",
                    "https://i.ytimg.com/vi/K3HQeCCHCns/hqdefault.jpg",
                    "Sky",
                    "https://muzati.net/music/0-0-1-18352-21",
                    0
                ),
                TrackModel(
                    4,
                    "Minsk",
                    "https://славянский-сайт.com/wa-data/public/shop/img/%D0%BE%D0%B1%D0%B5%D1%80%D0%B5%D0%" +
                            "B3%20%D0%B0%D0%BB%D0%B0%D1%82%D1%8B%D1%80%D1%8C%20%D0%B7%D0%BD%D0%B0%D1%87%D0%B5%D0%BD%D0%B8%D0%B5.jpg",
                    "Minsk",
                    "https://muzati.net/music/0-0-1-18352-21",
                    0
                ),
                TrackModel(
                    5,
                    "Brest",
                    "https://kursk.master-run.ru/image/catalog/naklejki/bez-kruga/makosh_1.jpg",
                    "Brest",
                    "https://muzati.net/music/0-0-1-18352-21",
                    0
                ),
                TrackModel(
                    6,
                    "Star",
                    "https://sticker-na-auto.ru/images/product/l/3701992d02.jpg",
                    "Star",
                    "https://muzati.net/music/0-0-1-18352-21",
                    0
                )
            )
        )
    }

    override suspend fun downloadFile(fileUrl: String): Response<ResponseBody> {
        TODO("Not yet implemented")
    }
}