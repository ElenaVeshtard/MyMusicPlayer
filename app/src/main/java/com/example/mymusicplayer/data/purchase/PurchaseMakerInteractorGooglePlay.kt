package com.example.mymusicplayer.data.purchase

import android.app.Activity
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.ProductDetails
import com.example.mymusicplayer.domain.purchase.ProductEntity
import com.example.mymusicplayer.domain.purchase.PurchaseMakeInteractor

class PurchaseMakerInteractorGooglePlay(
    private val activity: Activity,
    val purchaseStateInteractorGooglePlay: PurchaseStateInteractorGooglePlay
) :
    PurchaseMakeInteractor {

    override suspend fun makePurchase(product: ProductEntity) {

        val flowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(
                mutableListOf(
                    BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(product.originalObject as ProductDetails).build()
                )
            )
            .build()

        val responseCode =
            purchaseStateInteractorGooglePlay.billingClient.launchBillingFlow(activity, flowParams)

        if (responseCode.responseCode == BillingClient.BillingResponseCode.OK)
            purchaseStateInteractorGooglePlay._isPremium.value = true
    }
}
