package com.example.provinceprotodatastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.provinceprotodatastore.api.ApiClient
import com.example.provinceprotodatastore.repository.ProvinceRepository
import com.example.provinceprotodatastore.viewmodel.ProvinceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository = ProvinceRepository(this)
        val apiService = ApiClient.service
        val viewModel = ProvinceViewModel(repository,apiService )

        setContent {
            Navigation(viewModel)

        }
    }
}



