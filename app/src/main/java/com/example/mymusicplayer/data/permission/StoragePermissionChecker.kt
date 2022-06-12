package com.example.mymusicplayer.data.permission

import kotlinx.coroutines.flow.StateFlow

interface StoragePermissionChecker {

    val storagePermission: StateFlow<PermissionState>
    fun startPermissionDialog()
}

enum class PermissionState {
    HAS_PERMISSION, NO_PERMISSION, REJECTED_FOREVER, REJECTED_ASK_AGAIN
}