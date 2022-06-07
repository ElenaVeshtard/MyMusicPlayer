package com.example.mymusicplayer.data.purchase

import com.android.billingclient.api.ProductDetails
import com.example.mymusicplayer.domain.purchase.PeriodType
import com.example.mymusicplayer.domain.purchase.ProductEntity

class GooglePlayProductMapper: Mapper<ProductDetails, ProductEntity> {
    override fun map(from: ProductDetails): ProductEntity {
        return ProductEntity(PeriodType.MONTH, from.oneTimePurchaseOfferDetails!!.formattedPrice, from)
    }
}
