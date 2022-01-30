package com.demo.transactionrecordproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.transactionrecordproject.data.database.entity.Transaction
import com.demo.transactionrecordproject.domain.entity.TransactionReportBaseEntity
import com.demo.transactionrecordproject.domain.usecases.DeleteTransactionUseCase
import com.demo.transactionrecordproject.domain.usecases.FetchTransactionListUseCase
import com.demo.transactionrecordproject.domain.usecases.InsertTransactionUseCase
import com.demo.transactionrecordproject.utility.TransactionType
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class TransactionRecordViewModel @Inject constructor(
    private val insertTransactionUseCase: InsertTransactionUseCase,
    private val fetchTransactionListUseCase: FetchTransactionListUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase
) : ViewModel() {

    var transactionTypeArray = arrayOf(TransactionType.EXPENSE, TransactionType.INCOME)
    var transaction: Transaction? = null
    var selectedDate: Date? = null
    var selectedTransactionType: TransactionType? = null

    private val _transactions = MutableLiveData<List<TransactionReportBaseEntity>>()
    val transactions: LiveData<List<TransactionReportBaseEntity>>
        get() = _transactions

    private val _isSuccessful = MutableLiveData(false)
    val isSuccessful: LiveData<Boolean>
        get() = _isSuccessful

    fun getAllTransactions() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchTransactionListUseCase()
            }.onSuccess {
                _transactions.value = it
            }
        }
    }

    fun insertTransaction() {
        transaction?.let {
            viewModelScope.launch {
                kotlin.runCatching {
                    insertTransactionUseCase(it)
                }.onSuccess {
                    _isSuccessful.value = true
                    getAllTransactions()
                }.onFailure {
                    _isSuccessful.value = false
                }
            }
        }
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch {
            kotlin.runCatching {
                deleteTransactionUseCase(transaction)
            }.onSuccess {
                getAllTransactions()
            }
        }
    }

    fun fillTransactionValues(
        description: String,
        date: String,
        amount: Double,
        type: TransactionType?
    ) {
        type?.let {
            transaction = Transaction(
                description = description,
                date = date, amount = amount,
                type = type
            )
        }
    }

    fun getTotalExpense() = fetchTransactionListUseCase.totalExpense

    fun getTotalIncome() = fetchTransactionListUseCase.totalIncome

    fun getTotalBalance() = fetchTransactionListUseCase.balance

    fun getProgressValue() = fetchTransactionListUseCase.progressValue

    fun removeSelectedTransaction() {
        transaction = null
    }
}