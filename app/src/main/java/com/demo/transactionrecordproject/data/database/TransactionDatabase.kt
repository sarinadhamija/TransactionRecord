package com.demo.transactionrecordproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demo.transactionrecordproject.data.database.dao.TransactionDao
import com.demo.transactionrecordproject.data.database.entity.Transaction
import com.demo.transactionrecordproject.utility.DateTimeConverter

@Database(entities = [Transaction::class], version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class TransactionDatabase : RoomDatabase(){
    abstract fun transactionDao() : TransactionDao
}