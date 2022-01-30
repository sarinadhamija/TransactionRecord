package com.demo.transactionrecordproject.domain.usecases

import com.demo.transactionrecordproject.data.database.entity.Transaction
import com.demo.transactionrecordproject.data.repository.TransactionRepository
import com.demo.transactionrecordproject.domain.common.BaseUseCase
import javax.inject.Inject

class InsertTransactionUseCase @Inject constructor(transactionRepository: TransactionRepository) :
    BaseUseCase<TransactionRepository>(transactionRepository) {
        suspend operator fun invoke(transaction: Transaction) = repository.insertTransaction(transaction)
}