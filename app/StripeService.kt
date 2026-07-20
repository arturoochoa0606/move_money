package com.movemoney.backend.services

import com.stripe.Stripe
import com.stripe.model.PaymentIntent
import com.stripe.param.PaymentIntentCreateParams

object StripeService {
    init {
        // ⚠️ Usa tu clave secreta de Stripe aquí
        Stripe.apiKey = System.getenv("STRIPE_SECRET_KEY")
    }

    fun createPaymentIntent(amount: Long, currency: String = "usd"): PaymentIntent {
        val params = PaymentIntentCreateParams.builder()
            .setAmount(amount) // en centavos
            .setCurrency(currency)
            .build()
        return PaymentIntent.create(params)
    }
}
