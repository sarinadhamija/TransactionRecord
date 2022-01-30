package com.demo.transactionrecordproject.domain.entity

import java.io.Serializable

abstract class TransactionReportBaseEntity :Serializable {
    abstract val viewType : Int
}