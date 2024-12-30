package com.example.provinceprotodatastore.api

import com.example.provinceprotodatastore.model.Provinces
import retrofit2.http.GET

interface ProvinceApiService {
    @GET("refs/heads/master/province.json")
    suspend fun getProvinces() : List<Provinces>

}

