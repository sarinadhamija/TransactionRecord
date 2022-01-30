package com.demo.transactionrecordproject.domain.entity

import com.demo.transactionrecordproject.data.database.entity.Transaction
import com.demo.transactionrecordproject.utility.TransactionReportItemViewType

data class TransactionReportItemEntity(val data : Transaction) : TransactionReportBaseEntity() {
    override val viewType: Int
        get() = TransactionReportItemViewType.TRANSACTION_DETAIL
}