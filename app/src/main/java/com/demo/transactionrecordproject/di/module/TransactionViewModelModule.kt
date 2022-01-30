package com.demo.transactionrecordproject.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.transactionrecordproject.utility.TransactionViewModelKey
import com.demo.transactionrecordproject.presentation.viewmodel.TransactionRecordViewModel
import com.demo.transactionrecordproject.utility.TransactionReportViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TransactionViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory : TransactionReportViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @TransactionViewModelKey(TransactionRecordViewModel::class)
    abstract fun bindTransactionListViewModel(viewModel : TransactionRecordViewModel) : ViewModel
}