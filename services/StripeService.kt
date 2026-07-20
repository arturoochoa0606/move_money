package com.movemoney.services

import android.content.Context
import com.stripe.android.PaymentConfiguration
import com.stripe.android.Stripe
import com.stripe.android.model.CardParams
import com.stripe.android.model.PaymentMethodCreateParams
import com.stripe.android.model.PaymentIntentParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StripeService(private val context: Context, private val publishableKey: String) {

    private val stripe: Stripe

    init {
        // Inicializa Stripe con tu clave pública
        PaymentConfiguration.init(context, publishableKey)
        stripe = Stripe(context, publishableKey)
    }

    /**
     * Crear un PaymentMethod a partir de los datos de tarjeta
     */
    suspend fun createPaymentMethod(cardNumber: String, expMonth: Int, expYear: Int, cvc: String): PaymentMethodCreateParams {
        val cardParams = CardParams(
            number = cardNumber,
            expMonth = expMonth,
            expYear = expYear,
            cvc = cvc
        )
        return PaymentMethodCreateParams.create(cardParams)
    }

    /**
     * Confirmar un PaymentIntent con el clientSecret que viene del backend
     */
    suspend fun confirmPayment(clientSecret: String, paymentMethodParams: PaymentMethodCreateParams): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val params = PaymentIntentParams.createConfirmPaymentIntentWithPaymentMethodCreateParams(
                    paymentMethodParams,
                    clientSecret
                )
                stripe.confirmPayment(this@StripeService.context as androidx.activity.ComponentActivity, params)
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}
