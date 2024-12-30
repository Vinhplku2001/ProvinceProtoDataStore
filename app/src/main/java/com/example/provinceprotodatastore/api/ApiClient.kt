package com.example.provinceprotodatastore.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://raw.githubusercontent.com/dzungvu/srtRepo/"

    val service: ProvinceApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProvinceApiService::class.java)
}