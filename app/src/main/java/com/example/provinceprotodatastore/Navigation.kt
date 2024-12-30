package com.example.provinceprotodatastore

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.provinceprotodatastore.Screen.FavoriteProvinceScreen
import com.example.provinceprotodatastore.Screen.ProvinceScreen
import com.example.provinceprotodatastore.viewmodel.ProvinceViewModel

@Composable
fun Navigation(viewModel: ProvinceViewModel){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "ProvinceScreen"
    ) {
        composable("ProvinceScreen") {
            ProvinceScreen(viewModel = viewModel, navController = navController)
        }
        composable("FavoriteProvinceScreen") {
            FavoriteProvinceScreen(viewModel = viewModel, navController = navController)
        }

    }
}