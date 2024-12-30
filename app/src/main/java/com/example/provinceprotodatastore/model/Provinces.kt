package com.example.provinceprotodatastore.model

import com.google.gson.annotations.SerializedName

data class Provinces(
    @SerializedName("province_id")
    val provinceId: String,
    @SerializedName("province_name")
    val provinceName: String,
    @SerializedName("province_type")
    val provinceType: String
)
