package com.example.mymusicplayer.domain.purchase

import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PurchaseStateInteractorFake(sharedPreferences: SharedPreferences) : PurchaseStateInteractor {

    val _isPremium = MutableStateFlow(sharedPreferences.getBoolean("isPurchased", false))

    // (false)
    override val isPremium: StateFlow<Boolean> = _isPremium

    override suspend fun getListPurchases(): List<ProductEntity> {
        val productList = ArrayList<ProductEntity>()
        productList.add(ProductEntity(PeriodType.MONTH, "Monthly subscription - 4$", false))
        productList.add(ProductEntity(PeriodType.YEAR, "Subscription for a year - 10$", false))
        return productList
    }
}