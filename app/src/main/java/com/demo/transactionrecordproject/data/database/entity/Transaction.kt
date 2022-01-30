package com.demo.transactionrecordproject.data.database.entity

import androidx.room.*
import com.demo.transactionrecordproject.utility.DateTimeConverter
import com.demo.transactionrecordproject.utility.TransactionType
import java.util.*

@Entity(tableName = "transaction_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "type")
    val type: TransactionType,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "amount")
    val amount: Double,

    @ColumnInfo(name = "date")
    val date: String
)