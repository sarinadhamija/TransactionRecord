package com.demo.transactionrecordproject.di.module

import com.demo.transactionrecordproject.data.datasource.TransactionDataSource
import com.demo.transactionrecordproject.domain.source.TransactionLocalDataSource
import dagger.Module
import dagger.Provides

@Module
class TransactionDataSourceModule {

    @Provides
    fun provideTransactionDataSource(transactionLocalDataSource: TransactionLocalDataSource) : TransactionDataSource = transactionLocalDataSource

}