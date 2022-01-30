package com.demo.transactionrecordproject.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.transactionrecordproject.R
import com.demo.transactionrecordproject.application.TransactionApplication
import com.demo.transactionrecordproject.databinding.FragmentTransactionListBinding
import com.demo.transactionrecordproject.domain.entity.TransactionReportItemEntity
import com.demo.transactionrecordproject.presentation.adapter.TransactionReportListAdapter
import com.demo.transactionrecordproject.presentation.viewmodel.TransactionRecordViewModel
import com.demo.transactionrecordproject.utility.TransactionReportViewModelFactory
import javax.inject.Inject

class TransactionRecordFragment : Fragment(),
    TransactionReportListAdapter.TransactionReportListAdapterListener {

    @Inject
    lateinit var viewModelFactory: TransactionReportViewModelFactory

    private val viewModel: TransactionRecordViewModel
        get() = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[TransactionRecordViewModel::class.java]

    private var _binding: FragmentTransactionListBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as TransactionApplication).getFeatureComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllTransactions()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.getProgressValue().observe(
            this, {
                if (it > 5){
                    binding.progressBalance.visibility = View.VISIBLE
                } else {
                    binding.progressBalance.visibility = View.GONE
                }

                binding.progressBalance.progress = it
            }
        )

        binding.fabAddTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_TransactionListFragment_to_AddTransactionDialogFragment)
        }

        val adapter = TransactionReportListAdapter(this)
        binding.rvTransactionRecord.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvTransactionRecord.adapter = adapter
        viewModel.transactions.observe(
            this, {
                binding.tvNoRecordsAvailable.isVisible = it.isEmpty()
                binding.rvTransactionRecord.isVisible = it.isNotEmpty()
                if (it.isNotEmpty())
                    adapter.setData(it)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun deleteTransactionClicked(transaction: TransactionReportItemEntity) {
        viewModel.deleteTransaction(transaction.data)
    }
}