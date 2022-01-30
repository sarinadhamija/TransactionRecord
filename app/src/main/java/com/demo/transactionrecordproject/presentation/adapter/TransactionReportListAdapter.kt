package com.demo.transactionrecordproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.demo.transactionrecordproject.BR
import com.demo.transactionrecordproject.R
import com.demo.transactionrecordproject.data.database.entity.Transaction
import com.demo.transactionrecordproject.databinding.LayoutTransactionListDateItemBinding
import com.demo.transactionrecordproject.databinding.LayoutTransactionListItemBinding
import com.demo.transactionrecordproject.domain.entity.TransactionReportBaseEntity
import com.demo.transactionrecordproject.domain.entity.TransactionReportItemEntity
import com.demo.transactionrecordproject.presentation.holder.TransactionReportBaseViewHolder
import com.demo.transactionrecordproject.utility.TransactionReportItemViewType
import com.demo.transactionrecordproject.utility.TransactionType
import java.lang.IllegalStateException

class TransactionReportListAdapter (val listener : TransactionReportListAdapterListener):
    RecyclerView.Adapter<TransactionReportBaseViewHolder<TransactionReportBaseEntity>>() {

    interface TransactionReportListAdapterListener{
        fun deleteTransactionClicked(transaction : TransactionReportItemEntity)
    }

    private var itemList: MutableList<TransactionReportBaseEntity> = ArrayList()

    inner class TransactionReportListViewHolder(val binding: LayoutTransactionListItemBinding) :
        TransactionReportBaseViewHolder<TransactionReportBaseEntity>(binding) {
        override fun setData(value: TransactionReportBaseEntity, position: Int) {
            binding.setVariable(BR.transactionReportItem, value)
            binding.setVariable(BR.transactionClickListener, listener)
            binding.executePendingBindings()

            value as TransactionReportItemEntity
            val amount = when(value.data.type){
                TransactionType.EXPENSE -> {
                    binding.root.context.getString(R.string.expense_amount, value.data.amount.toString())
                }
                TransactionType.INCOME -> {
                    binding.root.context.getString(R.string.income_amount, value.data.amount.toString())
                }
            }
            binding.tvItemAmount.text = amount
        }
    }

    inner class TransactionReportDateViewHolder(val binding: LayoutTransactionListDateItemBinding) :
        TransactionReportBaseViewHolder<TransactionReportBaseEntity>(binding) {
        override fun setData(value: TransactionReportBaseEntity, position: Int) {
            binding.setVariable(BR.transactionReportDate, value)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionReportBaseViewHolder<TransactionReportBaseEntity> {
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            TransactionReportItemViewType.DATE -> {
                return TransactionReportDateViewHolder(
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.layout_transaction_list_date_item,
                        parent,
                        false
                    )
                )
            }

            TransactionReportItemViewType.TRANSACTION_DETAIL -> {
                return TransactionReportListViewHolder(
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.layout_transaction_list_item,
                        parent,
                        false
                    )
                )
            }

            else -> {
                throw IllegalStateException("Unexpected View : $viewType")
            }
        }
    }

    override fun onBindViewHolder(
        holder: TransactionReportBaseViewHolder<TransactionReportBaseEntity>,
        position: Int) {
        holder.setData(itemList[position], position)
    }

    override fun getItemViewType(position: Int): Int = itemList[position].viewType

    override fun getItemCount(): Int = itemList.size

    fun setData(list : List<TransactionReportBaseEntity>) {
        val items = mutableListOf<TransactionReportBaseEntity>()
        items.addAll(list)
        this.itemList.clear()
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }
}