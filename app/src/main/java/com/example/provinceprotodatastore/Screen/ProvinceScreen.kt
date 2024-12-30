package com.example.provinceprotodatastore.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.provinceprotodatastore.Province
import com.example.provinceprotodatastore.viewmodel.ProvinceViewModel
import kotlinx.coroutines.launch

@Composable
fun ProvinceScreen(viewModel: ProvinceViewModel = hiltViewModel(), navController: androidx.navigation.NavController) {
    val provinces = viewModel.provincesFlow.collectAsState(initial = emptyList())
    val favoriteProvinces = viewModel.favoriteProvincesFlow.collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()
    val searchQuery = remember { mutableStateOf("") }
    //val isFetchButtonVisible = remember { mutableStateOf(true) }
    val isFetchButtonVisible = viewModel.isFetchButtonVisible.collectAsState(initial = true)

    val filteredProvinces = provinces.value.filter {
        it.provinceName.contains(searchQuery.value, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF53C77))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = searchQuery.value,
            shape = RoundedCornerShape(20.dp),
            onValueChange = { searchQuery.value = it },
            label = { Text("Search Province") },
            leadingIcon = {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black
                )
            },
            modifier = Modifier
                .fillMaxWidth()

        )

        if (isFetchButtonVisible.value) {
            Button(
                onClick = {viewModel.fetchProvinces()
                },

                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 6.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Fetch Provinces", color = Color.White)
            }
        }
        Button(
            onClick = { navController.navigate("FavoriteProvinceScreen") },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
        )
        ){
            Text(text = "Favorites")
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredProvinces.size) { index ->
                val province = filteredProvinces[index]
                val isFavorite = favoriteProvinces.value.contains(province)
                ProvinceCard(
                    province = province,
                    onFavoriteClick = {
                        coroutineScope.launch {
                            viewModel.toggleFavorite(province)
                        }
                    },
                    isFavorite = isFavorite,


                )
            }
        }
    }
}

@Composable
fun ProvinceCard(province: Province, onFavoriteClick: (Province) -> Unit, isFavorite: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = "Province ID: ${province.provinceId}",
                style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                color = Color(0xFF17A5EC)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Province Name: ${province.provinceName}",
                style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                color = Color(0xFFD3BD14)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Province Type: ${province.provinceType}",
                style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                color = Color(0xFFC01D0F)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onFavoriteClick(province) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 4.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFavorite) Color.Red else Color.Green,
                    contentColor = Color.White
                )
            ) {
                Text(text = if (isFavorite) "Remove from Favorites" else "Add to Favorites")
            }
        }
    }
}



