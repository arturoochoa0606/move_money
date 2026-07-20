package com.movemoney.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movemoney.data.*
import com.movemoney.services.PaymentService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WalletViewModel(private val db: AppDatabase) : ViewModel() {
    private val _cards = MutableStateFlow<List<CardEntity>>(emptyList())
    val cards = _cards.asStateFlow()

    private val _transactions = MutableStateFlow<List<TransactionEntity>>(emptyList())
    val transactions = _transactions.asStateFlow()

    private val _funds = MutableStateFlow(0.0)
    val funds = _funds.asStateFlow()

    init {
        viewModelScope.launch {
            _cards.value = db.cardDao().getAll()
            _transactions.value = db.transactionDao().getAll()
        }
    }

    fun addCard(card: CardEntity) {
        viewModelScope.launch {
            db.cardDao().insert(card)
            _cards.value = db.cardDao().getAll()
        }
    }

    fun recharge(amount: Double, card: CardEntity) {
        viewModelScope.launch {
            if (PaymentService.recharge(amount, card)) {
                _funds.value += amount
                db.transactionDao().insert(TransactionEntity(from = card.number, to = "AppFunds", amount = amount))
                _transactions.value = db.transactionDao().getAll()
            }
        }
    }

    fun withdraw(amount: Double, card: CardEntity) {
        viewModelScope.launch {
            if (_funds.value >= amount && PaymentService.withdraw(amount, card)) {
                _funds.value -= amount
                db.transactionDao().insert(TransactionEntity(from = "AppFunds", to = card.number, amount = amount))
                _transactions.value = db.transactionDao().getAll()
            }
        }
    }

    fun externalTransfer(fromCard: CardEntity, toAccount: String, amount: Double) {
        viewModelScope.launch {
            if (PaymentService.externalTransfer(fromCard, toAccount, amount)) {
                db.transactionDao().insert(TransactionEntity(from = fromCard.number, to = toAccount, amount = amount))
                _transactions.value = db.transactionDao().getAll()
            }
        }
    }
}
