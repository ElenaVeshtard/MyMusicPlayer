package com.example.mymusicplayer.data.datastore

import kotlinx.serialization.Serializable

@Serializable
data class ApplicationPrefs(val turnToastOnLoading: Boolean)
