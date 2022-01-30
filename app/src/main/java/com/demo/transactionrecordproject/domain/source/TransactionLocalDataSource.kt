package com.demo.transactionrecordproject.domain.source

import com.demo.transactionrecordproject.data.database.TransactionDatabase
import com.demo.transactionrecordproject.data.datasource.TransactionDataSource
import com.demo.transactionrecordproject.data.database.entity.Transaction
import com.demo.transactionrecordproject.utility.TransactionType
import javax.inject.Inject

class TransactionLocalDataSource @Inject constructor(private val database: TransactionDatabase) :
    TransactionDataSource {
    override suspend fun getAllTransactions(): List<Transaction> =
        database.transactionDao().getAllTransactions()

    override suspend fun insertTransaction(transaction: Transaction) =
        database.transactionDao().insert(transaction)

    override suspend fun deleteTransaction(transaction: Transaction) {
        database.transactionDao().delete(transaction)
    }

    override suspend fun deleteAllTransactions() {
        database.transactionDao().deleteAllTransactions()
    }

    override suspend fun getAllDistinctDates(): List<String> =
        database.transactionDao().getAllDistinctDates()

    override suspend fun getAllTransactions(date: String): List<Transaction> =
        database.transactionDao().getAllTransactions(date)

    override suspend fun getTotal(transactionType : TransactionType): Double =
        database.transactionDao().getTotal(transactionType)

}