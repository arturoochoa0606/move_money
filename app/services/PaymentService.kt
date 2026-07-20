package com.movemoney.services

import com.movemoney.data.CardEntity

object PaymentService {
    suspend fun recharge(amount: Double, card: CardEntity): Boolean {
        // Aquí iría la llamada real a Stripe o PayPal
        println("Recarga simulada de $amount desde tarjeta ${card.number}")
        return true
    }

    suspend fun withdraw(amount: Double, card: CardEntity): Boolean {
        println("Transferencia simulada de $amount hacia tarjeta ${card.number}")
        return true
    }

    suspend fun externalTransfer(fromCard: CardEntity, toAccount: String, amount: Double): Boolean {
        println("Transferencia externa simulada de $amount desde ${fromCard.number} a $toAccount")
        return true
    }
}
