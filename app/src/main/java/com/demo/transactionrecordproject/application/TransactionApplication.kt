package com.demo.transactionrecordproject.application

import android.app.Application
import com.demo.transactionrecordproject.di.component.DaggerTransactionFeatureComponent
import com.demo.transactionrecordproject.di.component.TransactionFeatureComponent
import com.demo.transactionrecordproject.di.module.TransactionDatabaseModule

class TransactionApplication : Application() {
    private lateinit var transactionFeatureComponent: TransactionFeatureComponent

    override fun onCreate() {
        super.onCreate()
        initializeTransactionFeatureComponent()
    }

    private fun initializeTransactionFeatureComponent() {
        transactionFeatureComponent = DaggerTransactionFeatureComponent.builder()
            .application(this)
            .dbModule(TransactionDatabaseModule())
            .build()

        transactionFeatureComponent.inject(this)
    }

    fun getFeatureComponent() = transactionFeatureComponent
}