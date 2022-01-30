package com.demo.transactionrecordproject.data.database.dao

import androidx.room.*
import com.demo.transactionrecordproject.data.database.entity.Transaction
import com.demo.transactionrecordproject.utility.TransactionType

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Query("delete from transaction_table")
    suspend fun deleteAllTransactions()

    @Query("select * from transaction_table order by date DESC")
    suspend fun getAllTransactions() : List<Transaction>

    @Query("select * from transaction_table where date LIKE '%' || :currentDate || '%'")
    suspend fun getAllTransactions(currentDate : String) : List<Transaction>

    @Query("select distinct date from transaction_table")
    suspend fun getAllDistinctDates() : List<String>

    @Query("select SUM(amount) from transaction_table where type LIKE '%' || :transactionType || '%'")
    suspend fun getTotal(transactionType : TransactionType) : Double
}