package com.example.mymusicplayer.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "albums")
@Serializable
data class AlbumModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val image: String,
    val name: String,
    @SerialName("trackCount")
    val trackCount: Int,
    var sourceType: Int
)