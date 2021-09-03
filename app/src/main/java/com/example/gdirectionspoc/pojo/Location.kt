package com.example.gdirectionspoc.pojo

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lang: Double,
)
