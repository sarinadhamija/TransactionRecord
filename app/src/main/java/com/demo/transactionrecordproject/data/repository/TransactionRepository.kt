package com.demo.transactionrecordproject.data.repository

import com.demo.transactionrecordproject.data.common.BaseRepository
import com.demo.transactionrecordproject.data.database.entity.Transaction
import com.demo.transactionrecordproject.data.datasource.TransactionDataSource
import com.demo.transactionrecordproject.utility.TransactionType
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val transactionDataSource: TransactionDataSource) :
    BaseRepository<TransactionDataSource>(transactionDataSource) {

    suspend fun fetchAllTransactions(): List<Transaction> = dataSource.getAllTransactions()

    suspend fun fetchAllDistinctDates(): List<String> = dataSource.getAllDistinctDates()

    suspend fun fetchAllTransactions(date : String): List<Transaction> = dataSource.getAllTransactions(date)

    suspend fun insertTransaction(transaction: Transaction) = dataSource.insertTransaction(transaction)

    suspend fun deleteTransaction(transaction: Transaction) = dataSource.deleteTransaction(transaction)

    suspend fun getTotal(transactionType: TransactionType) : Double = dataSource.getTotal(transactionType)

}