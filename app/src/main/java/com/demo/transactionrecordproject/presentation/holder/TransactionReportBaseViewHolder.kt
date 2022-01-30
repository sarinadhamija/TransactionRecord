package com.demo.transactionrecordproject.presentation.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.demo.transactionrecordproject.domain.entity.TransactionReportBaseEntity

abstract class TransactionReportBaseViewHolder<T : TransactionReportBaseEntity>(binding : ViewDataBinding) :  RecyclerView.ViewHolder(binding.root){
    abstract fun setData(value : T, position : Int)
}