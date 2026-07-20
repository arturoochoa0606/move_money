package com.movemoney.services

import android.app.Application
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.createorder.CreateOrderActions
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.order.OrderIntent
import com.paypal.checkout.order.PurchaseUnit

class PayPalService(app: Application, clientId: String) {

    init {
        PayPalCheckout.setConfig(
            CheckoutConfig(
                application = app,
                clientId = clientId,
                environment = com.paypal.checkout.config.Environment.SANDBOX,
                returnUrl = "com.movemoney://paypalpay"
            )
        )
    }

    fun createOrder(amount: String): CreateOrder {
        return CreateOrder { createOrderActions: CreateOrderActions ->
            val purchaseUnit = PurchaseUnit.Builder()
                .amount(com.paypal.checkout.order.Amount.Builder()
                    .currencyCode("USD")
                    .value(amount)
                    .build())
                .build()

            val order = com.paypal.checkout.order.Order.Builder()
                .intent(OrderIntent.CAPTURE)
                .purchaseUnitList(listOf(purchaseUnit))
                .build()

            createOrderActions.create(order)
        }
    }
}
