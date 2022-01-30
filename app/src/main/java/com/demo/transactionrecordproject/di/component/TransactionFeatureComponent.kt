package com.demo.transactionrecordproject.di.component

import android.app.Application
import com.demo.transactionrecordproject.application.TransactionApplication
import com.demo.transactionrecordproject.di.module.*
import com.demo.transactionrecordproject.presentation.view.TransactionRecordFragment
import com.demo.transactionrecordproject.presentation.view.AddTransactionDialogFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        TransactionRepositoryModule::class,
        TransactionUseCaseModule::class,
        TransactionViewModelModule::class,
        TransactionDatabaseModule::class,
        TransactionDataSourceModule::class,
        TransactionCoroutineScopeModule::class
    ]
)
interface TransactionFeatureComponent {
    fun inject(application: TransactionApplication)
    fun inject(transactionRecordFragment: TransactionRecordFragment)
    fun inject(addTransactionDialogFragment: AddTransactionDialogFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun dbModule(dbModule: TransactionDatabaseModule): Builder

        fun build(): TransactionFeatureComponent
    }
}