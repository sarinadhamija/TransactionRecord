package com.demo.transactionrecordproject.di.module

import com.demo.transactionrecordproject.data.datasource.TransactionDataSource
import com.demo.transactionrecordproject.data.repository.TransactionRepository
import dagger.Module
import dagger.Provides

@Module
class TransactionRepositoryModule {

    @Provides
    fun provideTransactionRepository(transactionDataSource : TransactionDataSource) = TransactionRepository(transactionDataSource)

}