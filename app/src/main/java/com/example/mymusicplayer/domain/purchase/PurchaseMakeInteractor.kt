package com.example.mymusicplayer.domain.purchase

interface PurchaseMakeInteractor {
    suspend fun makePurchase(product: ProductEntity)
}
