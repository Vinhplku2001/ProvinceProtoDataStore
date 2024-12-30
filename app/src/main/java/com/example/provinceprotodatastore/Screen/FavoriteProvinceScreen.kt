package com.example.provinceprotodatastore.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.provinceprotodatastore.Province
import com.example.provinceprotodatastore.viewmodel.ProvinceViewModel

@Composable
fun FavoriteProvinceScreen(navController: NavController, viewModel: ProvinceViewModel) {
    val favoriteProvinces = viewModel.favoriteProvincesFlow.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF53C77)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Favorites List", style = MaterialTheme.typography.titleLarge)

        Button(
            onClick = { navController.navigate("ProvinceScreen") },
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                 containerColor = Color.Blue,
                contentColor = Color.White,

        )
        ){
            Text("Back to Provinces")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(favoriteProvinces.value.size) { index ->
                val province = favoriteProvinces.value[index]
                FavoriteProvinceCard(province = province)
            }
        }
    }
}
@Composable
    fun FavoriteProvinceCard(province: Province) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {

                Text(text = "Province ID: ${province.provinceId}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF17A5EC))
                Text(text = "Province Name: ${province.provinceName}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFD3BD14))
                Text(text = "Province Type: ${province.provinceType}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFC01D0F))

        }
    }
}


