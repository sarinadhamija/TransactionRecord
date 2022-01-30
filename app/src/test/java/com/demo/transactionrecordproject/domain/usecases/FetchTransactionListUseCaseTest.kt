package com.demo.transactionrecordproject.domain.usecases

import com.demo.transactionrecordproject.data.datasource.TransactionDataSource
import com.demo.transactionrecordproject.data.repository.TransactionRepository
import org.mockito.Mock

class FetchTransactionListUseCaseTest : BaseUseCaseTest(){

    lateinit var fetchTransactionListUseCase: FetchTransactionListUseCase

    @Mock
    lateinit var transactionDataSource : TransactionDataSource

    override fun beforeTest() {
        super.beforeTest()
        fetchTransactionListUseCase = FetchTransactionListUseCase(TransactionRepository(transactionDataSource))
    }
}