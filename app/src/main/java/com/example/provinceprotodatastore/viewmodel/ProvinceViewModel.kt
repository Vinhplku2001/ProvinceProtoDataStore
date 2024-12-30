package com.example.provinceprotodatastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.provinceprotodatastore.Province
import com.example.provinceprotodatastore.api.ProvinceApiService
import com.example.provinceprotodatastore.repository.ProvinceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProvinceViewModel @Inject constructor(
    private val repository: ProvinceRepository,
    private val apiService: ProvinceApiService

):ViewModel(){
    val provincesFlow : Flow<List<Province>> = repository.provincesFlow
    val favoriteProvincesFlow : Flow<List<Province>> = repository.favoriteProvincesFlow


    private val _isFetchButtonVisible = MutableStateFlow(true)
    val isFetchButtonVisible: Flow<Boolean> = _isFetchButtonVisible


    fun fetchProvinces() {
        viewModelScope.launch{
            val provincesFromApi = apiService.getProvinces().map{
                Province.newBuilder()
                    .setProvinceId(it.provinceId)
                    .setProvinceName(it.provinceName)
                    .setProvinceType(it.provinceType)
                    .build()

            }
            repository.saveProvinces(provincesFromApi)

            _isFetchButtonVisible.value = false
        }
    }
    fun toggleFavorite(province: Province) {
        viewModelScope.launch {
            repository.toggleFavorite(province)
        }
    }

}
