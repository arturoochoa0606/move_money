package com.movemoney.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*
import io.ktor.client.request.*

data class PaymentRequest(val amount: Long, val currency: String = "usd")
data class PaymentResponse(val clientSecret: String)

object PaymentApi {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            jackson()
        }
    }

    private const val BASE_URL = "http://10.0.2.2:8080" // localhost para emulador Android

    suspend fun createPaymentIntent(amount: Long, currency: String = "usd"): String {
        val response: PaymentResponse = client.post("$BASE_URL/create-payment-intent") {
            setBody(PaymentRequest(amount, currency))
        }.body()
        return response.clientSecret
    }
}
