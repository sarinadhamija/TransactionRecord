package com.demo.transactionrecordproject.data.datasource

import com.demo.transactionrecordproject.data.common.BaseDataSource
import com.demo.transactionrecordproject.data.database.entity.Transaction
import com.demo.transactionrecordproject.utility.TransactionType

interface TransactionDataSource : BaseDataSource{
    suspend fun insertTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transaction: Transaction)
    suspend fun deleteAllTransactions()
    suspend fun getAllDistinctDates() : List<String>
    suspend fun getAllTransactions(date : String) : List<Transaction>
    suspend fun getAllTransactions() : List<Transaction>
    suspend fun getTotal(transactionType : TransactionType) : Double
}