package com.example.provinceprotodatastore.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.example.provinceprotodatastore.Province
import com.example.provinceprotodatastore.ProvinceList
import com.example.provinceprotodatastore.ProvinceListSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File

class ProvinceRepository(
    private val context: Context){

    private val dataStore : DataStore<ProvinceList> = DataStoreFactory.create(
        serializer = ProvinceListSerializer,
        produceFile = { File(context.filesDir, "provinces.pb") }
    )
    private val favoriteProvincesDataStore: DataStore<ProvinceList> = DataStoreFactory.create(
        serializer = ProvinceListSerializer,
        produceFile = { File(context.filesDir, "favorite_provinces.pb") }
    )

    suspend fun saveProvinces(provinces: List<Province>) {
        Log.d("ProvinceRepository", "Saving provinces: $provinces")
        dataStore.updateData {
            val updatedData = it.toBuilder().clearProvinces().addAllProvinces(provinces).build()
            Log.d("ProvinceRepository", "Provinces saved: $updatedData")
            updatedData
        }
    }
    suspend fun toggleFavorite(province: Province) {
        favoriteProvincesDataStore.updateData { currentData ->
            val updatedList = if (currentData.provincesList.contains(province)) {
                currentData.toBuilder()
                    .removeProvinces(currentData.provincesList.indexOf(province))
                    .build()
            } else {
                currentData.toBuilder().addProvinces(province).build()
            }
            updatedList
        }
    }

    val provincesFlow: Flow<List<Province>> = dataStore.data.map { provinceList ->
        Log.d("ProvinceRepository", "Retrieved provinces: ${provinceList.provincesList}")
        provinceList.provincesList
    }
    val favoriteProvincesFlow: Flow<List<Province>> = favoriteProvincesDataStore.data.map { it.provincesList }

}
