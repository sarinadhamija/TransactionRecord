package com.demo.transactionrecordproject.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.transactionrecordproject.crrules.TestCoroutineRule
import com.demo.transactionrecordproject.data.datasource.TransactionDataSource
import com.demo.transactionrecordproject.data.repository.TransactionRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

open class BaseUseCaseTest : TestCase(){
    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    open fun beforeTest(){
        MockitoAnnotations.initMocks(this)
    }

}