package com.demo.transactionrecordproject.domain.entity

import com.demo.transactionrecordproject.utility.TransactionReportItemViewType

data class TransactionReportDateEntity(val data : String) : TransactionReportBaseEntity() {
    override val viewType: Int
        get() = TransactionReportItemViewType.DATE
}