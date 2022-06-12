package com.example.mymusicplayer.presentation

import com.example.mymusicplayer.domain.purchase.ProductEntity
import kotlinx.coroutines.flow.StateFlow

class PurchaseViewModel(
    val purchase: StateFlow<Result<List<ProductEntity>>>,
    val isPremised: StateFlow<Boolean>
)