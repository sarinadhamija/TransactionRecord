package com.demo.transactionrecordproject.di.module

import com.demo.transactionrecordproject.data.repository.TransactionRepository
import com.demo.transactionrecordproject.domain.usecases.DeleteTransactionUseCase
import com.demo.transactionrecordproject.domain.usecases.FetchTransactionListUseCase
import com.demo.transactionrecordproject.domain.usecases.InsertTransactionUseCase
import dagger.Module
import dagger.Provides

@Module
class TransactionUseCaseModule {

    @Provides
    fun provideDeleteTransactionUseCase(transactionRepository: TransactionRepository) = DeleteTransactionUseCase(transactionRepository)

    @Provides
    fun provideFetchTransactionListUseCase(transactionRepository: TransactionRepository) = FetchTransactionListUseCase(transactionRepository)

    @Provides
    fun provideInsertTransactionUseCase(transactionRepository: TransactionRepository) = InsertTransactionUseCase(transactionRepository)

}