package com.demo.transactionrecordproject.domain.common

import com.demo.transactionrecordproject.data.common.BaseDataSource
import com.demo.transactionrecordproject.data.common.BaseRepository

abstract class BaseUseCase<T : BaseRepository<out BaseDataSource>>(val repository: T)