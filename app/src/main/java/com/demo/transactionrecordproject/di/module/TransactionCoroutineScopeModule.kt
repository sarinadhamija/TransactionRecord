package com.demo.transactionrecordproject.di.module

import com.demo.transactionrecordproject.utility.CoroutineScopeProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
class TransactionCoroutineScopeModule {
    @Provides
    fun provideFinanceRecordCoroutineScopeModule() : CoroutineScopeProvider = object :
        CoroutineScopeProvider {
        override fun getBackgroundThreadScope() = CoroutineScope(Dispatchers.IO)
        override fun getUIThreadScope() = CoroutineScope(Dispatchers.Main)
    }
}