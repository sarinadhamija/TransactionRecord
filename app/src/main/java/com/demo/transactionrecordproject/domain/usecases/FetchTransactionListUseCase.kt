package com.demo.transactionrecordproject.domain.usecases

import androidx.lifecycle.MutableLiveData
import com.demo.transactionrecordproject.data.database.entity.Transaction
import com.demo.transactionrecordproject.data.repository.TransactionRepository
import com.demo.transactionrecordproject.domain.common.BaseUseCase
import com.demo.transactionrecordproject.domain.entity.TransactionReportBaseEntity
import com.demo.transactionrecordproject.domain.entity.TransactionReportDateEntity
import com.demo.transactionrecordproject.domain.entity.TransactionReportItemEntity
import com.demo.transactionrecordproject.utility.TransactionType
import javax.inject.Inject
import kotlin.math.floor

class FetchTransactionListUseCase @Inject constructor(transactionRepository: TransactionRepository) :
    BaseUseCase<TransactionRepository>(transactionRepository) {

    private var transactionList: MutableList<Transaction> = mutableListOf()
    var totalIncome = MutableLiveData<Double>()
    var totalExpense = MutableLiveData<Double>()
    var balance = MutableLiveData<Double>()
    var progressValue = MutableLiveData<Int>()

    private var totalExpenseValue = 0.0
    private var totalIncomeValue = 0.0

    suspend operator fun invoke(): List<TransactionReportBaseEntity> =
        getTransactions()

    private suspend fun getTransactions(): List<TransactionReportBaseEntity> {
        transactionList.addAll(repository.fetchAllTransactions())

        totalExpense.value = repository.getTotal(TransactionType.EXPENSE)
        totalExpenseValue = totalExpense.value ?: 0.0

        totalIncome.value = repository.getTotal(TransactionType.INCOME)
        totalIncomeValue = totalIncome.value ?: 0.0

        balance.value = totalIncomeValue - totalExpenseValue
        setProgressValue()

        val convertedList = ArrayList<TransactionReportBaseEntity>()
        val dateList = ArrayList<String>()
        dateList.addAll(repository.fetchAllDistinctDates())
        for (date: String in dateList) {
            convertedList.add(TransactionReportDateEntity(date))
            convertedList.addAll(
                repository.fetchAllTransactions(date = date)
                    .map { TransactionReportItemEntity(it) })
        }
        return convertedList
    }

    private fun setProgressValue() {
     val progress = floor(totalExpenseValue / totalIncomeValue * 100).toInt()
        if (progress > totalIncomeValue){
            progressValue.value = 0
        } else {
            progressValue.value = progress
        }

    }
}