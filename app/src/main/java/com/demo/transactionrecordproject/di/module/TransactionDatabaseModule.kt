package com.demo.transactionrecordproject.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.demo.transactionrecordproject.data.database.TransactionDatabase
import com.demo.transactionrecordproject.data.database.entity.Transaction
import com.demo.transactionrecordproject.domain.entity.TransactionReportDateEntity
import com.demo.transactionrecordproject.domain.entity.TransactionReportItemEntity
import com.demo.transactionrecordproject.utility.Constants
import com.demo.transactionrecordproject.utility.TransactionType
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
class TransactionDatabaseModule {

    private lateinit var transactionRecordDatabase : TransactionDatabase

    @Provides
    @Singleton
    fun provideTransactionDatabase(application: Application) : TransactionDatabase {
        transactionRecordDatabase = Room.databaseBuilder(
            application,
            TransactionDatabase::class.java,
            Constants.Extras.DATABASE_NAME
        ).allowMainThreadQueries().build()
        return transactionRecordDatabase
    }

    @Provides
    @Singleton
    fun provideTransactionDao(database: TransactionDatabase) = database.transactionDao()
}