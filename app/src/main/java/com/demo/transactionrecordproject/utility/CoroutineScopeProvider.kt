package com.demo.transactionrecordproject.utility

import kotlinx.coroutines.CoroutineScope

interface CoroutineScopeProvider {
    fun getBackgroundThreadScope() : CoroutineScope
    fun getUIThreadScope() : CoroutineScope
}