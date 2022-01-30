package com.demo.transactionrecordproject.data.common

abstract class BaseRepository<T : BaseDataSource> (val dataSource: T)