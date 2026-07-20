package com.movemoney.backend.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import com.movemoney.backend.services.StripeService

data class PaymentRequest(val amount: Long, val currency: String = "usd")

fun Application.paymentRoutes() {
    routing {
        post("/create-payment-intent") {
            val request = call.receive<PaymentRequest>()
            val intent = StripeService.createPaymentIntent(request.amount, request.currency)
            call.respond(mapOf("clientSecret" to intent.clientSecret))
        }
    }
}
