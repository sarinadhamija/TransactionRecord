package com.demo.transactionrecordproject.presentation.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.demo.transactionrecordproject.R
import com.demo.transactionrecordproject.application.TransactionApplication
import com.demo.transactionrecordproject.databinding.FragmentTransactionAddBinding
import com.demo.transactionrecordproject.presentation.viewmodel.TransactionRecordViewModel
import com.demo.transactionrecordproject.utility.TransactionReportViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AddTransactionDialogFragment : DialogFragment() {

    @Inject
    lateinit var viewModelFactory: TransactionReportViewModelFactory

    private val viewModel: TransactionRecordViewModel
        get() = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        ).get(TransactionRecordViewModel::class.java)

    private var _binding: FragmentTransactionAddBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as TransactionApplication).getFeatureComponent().inject(this)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.removeSelectedTransaction()

        addSpinner()

        binding.ivDialogCross.setOnClickListener {
            dismiss()
        }

        binding.ivPriceIncrement.setOnClickListener {
            binding.etAddTransactionPrice.text.apply {
                if (this.toString().isNotEmpty()) {
                    val value: Double = this.toString().toDouble() + 1
                    binding.etAddTransactionPrice.setText(value.toString())
                } else {
                    val value = 1
                    binding.etAddTransactionPrice.setText(value.toString())
                }
            }
        }

        binding.ivPriceDecrement.setOnClickListener {
            binding.etAddTransactionPrice.text.apply {
                if (this.toString().isNotEmpty()) {
                    val value: Double = this.toString().toDouble() - 1
                    if (value >= 0.0) {
                        binding.etAddTransactionPrice.setText(value.toString())
                    } else {
                        binding.etAddTransactionPrice.setText("0.0")
                    }
                } else {
                    binding.etAddTransactionPrice.setText("1")
                }
            }
        }

        binding.tvAddTransactionDate.setOnClickListener {
            openDatePickerDialog()
        }

        binding.btnAddTransaction.setOnClickListener {
            if (checkIfEmpty()) {
                showToast("Please fill the mandatory fields")
            } else {
                viewModel.fillTransactionValues(
                    date = binding.tvAddTransactionDate.text.toString(),
                    amount = binding.etAddTransactionPrice.text.toString().toDouble(),
                    description = binding.tvAddTransactionDescription.text.toString(),
                    type = viewModel.selectedTransactionType
                )

                viewModel.insertTransaction()
            }

            viewModel.isSuccessful.observe(this, Observer {
                if (it) {
                    showToast("Transaction Successfully inserted")
                    dismiss()
                }
            })
        }
    }

    private fun showToast(message : String){
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun openDatePickerDialog() {
        val mCurrentDate: Calendar = Calendar.getInstance()
        var mYear = mCurrentDate.get(Calendar.YEAR)
        var mMonth = mCurrentDate.get(Calendar.MONTH)
        var mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH)
        val mDatePicker = DatePickerDialog(
            requireContext(),
            { datePicker, selectedYear, selectedMonth, selectedDay ->
                val myCalendar: Calendar = Calendar.getInstance()
                myCalendar.set(Calendar.YEAR, selectedYear)
                myCalendar.set(Calendar.MONTH, selectedMonth)
                myCalendar.set(Calendar.DAY_OF_MONTH, selectedDay)
                val myFormat = "dd MMM, yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
                binding.tvAddTransactionDate.text = sdf.format(myCalendar.time)
                viewModel.selectedDate = myCalendar.time
                mDay = selectedDay
                mMonth = selectedMonth
                mYear = selectedYear
            }, mYear, mMonth, mDay
        )
        mDatePicker.show()
    }

    private fun addSpinner() {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.layout_spinner_item,
            viewModel.transactionTypeArray
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTransactionType.adapter = adapter
        binding.spinnerTransactionType.onItemSelectedListener = itemClickListener
    }

    private fun checkIfEmpty(): Boolean {
        return binding.etAddTransactionPrice.text.isNullOrEmpty()
                || binding.tvAddTransactionDescription.text.isNullOrEmpty()
                || binding.tvAddTransactionDate.text.isNullOrEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val itemClickListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                viewModel.selectedTransactionType = viewModel.transactionTypeArray[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

}