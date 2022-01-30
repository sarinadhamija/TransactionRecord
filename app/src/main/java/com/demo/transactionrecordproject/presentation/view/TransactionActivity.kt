package com.demo.transactionrecordproject.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.transactionrecordproject.databinding.ActivityTransactionMainBinding

class TransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}