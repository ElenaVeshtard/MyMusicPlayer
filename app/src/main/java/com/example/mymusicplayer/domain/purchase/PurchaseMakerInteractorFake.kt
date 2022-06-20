package com.example.mymusicplayer.domain.purchase

import android.content.SharedPreferences

class PurchaseMakerInteractorFake(
    private val purchaseStateInteractorFake: PurchaseStateInteractorFake,
    private val sharedPreferences: SharedPreferences
) : PurchaseMakeInteractor {

    override suspend fun makePurchase(product: ProductEntity) {
        purchaseStateInteractorFake._isPremium.value = true

        sharedPreferences.edit()
            .putBoolean("isPurchased", true)
            .commit()
    }
}