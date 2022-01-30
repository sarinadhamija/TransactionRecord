package com.demo.transactionrecordproject.utils

import com.demo.transactionrecordproject.data.database.entity.Transaction
import com.demo.transactionrecordproject.domain.entity.TransactionReportBaseEntity
import com.demo.transactionrecordproject.domain.entity.TransactionReportDateEntity
import com.demo.transactionrecordproject.domain.entity.TransactionReportItemEntity
import com.demo.transactionrecordproject.utility.TransactionType

object TestUtils {

    val TEST_DATE_1 = "10 Oct, 2021"
    val TEST_DATE_2 = "11 Oct, 2021"

    val TEST_DESCRIPTION = "Test Description"
    val TEST_AMOUNT = 10.0

    val TEST_TRANSACTION_1 = Transaction(
        description = TEST_DESCRIPTION,
        date = TEST_DATE_1,
        amount = TEST_AMOUNT,
        type = TransactionType.INCOME
    )

    val TEST_TRANSACTION_2 = Transaction(
        description = TEST_DESCRIPTION,
        date = TEST_DATE_1,
        amount = TEST_AMOUNT,
        type = TransactionType.EXPENSE
    )

    val TEST_TRANSACTION_3 = Transaction(
        description = TEST_DESCRIPTION,
        date = TEST_DATE_2,
        amount = TEST_AMOUNT,
        type = TransactionType.EXPENSE
    )

    val TEST_TRANSACTION_4 = Transaction(
        description = TEST_DESCRIPTION,
        date = TEST_DATE_2,
        amount = TEST_AMOUNT,
        type = TransactionType.INCOME
    )

    fun getTransactionList(): List<TransactionReportBaseEntity> {
        val list = ArrayList<TransactionReportBaseEntity>()
        list.add(TransactionReportDateEntity(TEST_DATE_1))
        list.add(TransactionReportItemEntity(TEST_TRANSACTION_1))
        list.add(TransactionReportItemEntity(TEST_TRANSACTION_2))
        list.add(TransactionReportDateEntity(TEST_DATE_2))
        list.add(TransactionReportItemEntity(TEST_TRANSACTION_3))
        list.add(TransactionReportItemEntity(TEST_TRANSACTION_4))
        return list
    }


}