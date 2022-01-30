package com.demo.transactionrecordproject.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.transactionrecordproject.crrules.TestCoroutineRule
import com.demo.transactionrecordproject.domain.entity.TransactionReportItemEntity
import com.demo.transactionrecordproject.domain.usecases.DeleteTransactionUseCase
import com.demo.transactionrecordproject.domain.usecases.FetchTransactionListUseCase
import com.demo.transactionrecordproject.domain.usecases.InsertTransactionUseCase
import com.demo.transactionrecordproject.utility.TransactionType
import com.demo.transactionrecordproject.utils.TestUtils
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class TransactionRecordViewModelTest : TestCase() {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: TransactionRecordViewModel

    @Mock
    lateinit var insertTransactionUseCase: InsertTransactionUseCase

    @Mock
    lateinit var deleteTransactionUseCase: DeleteTransactionUseCase

    @Mock
    lateinit var fetchTransactionListUseCase: FetchTransactionListUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = TransactionRecordViewModel(
            insertTransactionUseCase = insertTransactionUseCase,
            deleteTransactionUseCase = deleteTransactionUseCase,
            fetchTransactionListUseCase = fetchTransactionListUseCase
        )

    }

    @ExperimentalCoroutinesApi
    @Test
    fun testFetchTransactionSuccess() {
        testCoroutineRule.runBlockingTest {
            val transactionList = TestUtils.getTransactionList()
            Mockito.`when`(fetchTransactionListUseCase()).thenReturn(transactionList)
            viewModel.getAllTransactions()
            Assert.assertEquals(viewModel.transactions.value, TestUtils.getTransactionList())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testInsertTransactionSuccess() {
        testCoroutineRule.runBlockingTest {
            viewModel.fillTransactionValues(
                TestUtils.TEST_DESCRIPTION,
                TestUtils.TEST_DATE_1,
                TestUtils.TEST_AMOUNT,
                TransactionType.INCOME
            )
            viewModel.insertTransaction()
            Assert.assertEquals(viewModel.transaction, TestUtils.TEST_TRANSACTION_1)
            Assert.assertEquals(viewModel.isSuccessful.value, true)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testInsertTransactionError() {
        testCoroutineRule.runBlockingTest {
            viewModel.fillTransactionValues(
                TestUtils.TEST_DESCRIPTION,
                TestUtils.TEST_DATE_1,
                TestUtils.TEST_AMOUNT,
                TransactionType.INCOME
            )
            Mockito.`when`(insertTransactionUseCase(TestUtils.TEST_TRANSACTION_1))
                .thenThrow(RuntimeException())
            viewModel.insertTransaction()
            Assert.assertEquals(viewModel.transaction, TestUtils.TEST_TRANSACTION_1)
            Assert.assertEquals(viewModel.isSuccessful.value, false)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testDeleteTransactionSuccess() {
        testCoroutineRule.runBlockingTest {
            val transactionList = TestUtils.getTransactionList()
            transactionList.toMutableList()
                .remove(TransactionReportItemEntity(TestUtils.TEST_TRANSACTION_1))

            Mockito.`when`(fetchTransactionListUseCase()).thenReturn(transactionList)

            viewModel.deleteTransaction(TestUtils.TEST_TRANSACTION_1)

            Assert.assertEquals(viewModel.transactions.value, transactionList)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testDeleteTransactionError() {
        testCoroutineRule.runBlockingTest {
            val list = TestUtils.getTransactionList()
            Mockito.`when`(deleteTransactionUseCase(TestUtils.TEST_TRANSACTION_1))
                .thenThrow(RuntimeException())

            viewModel.deleteTransaction(TestUtils.TEST_TRANSACTION_1)

            Assert.assertNull(viewModel.transactions.value)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testTransactionFilledSuccessful() {
        viewModel.fillTransactionValues(
            TestUtils.TEST_DESCRIPTION,
            TestUtils.TEST_DATE_1,
            TestUtils.TEST_AMOUNT,
            TransactionType.INCOME
        )

        Assert.assertEquals(viewModel.transaction, TestUtils.TEST_TRANSACTION_1)
    }


}